package sba.group3.backendmvc.service.laboratory.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabOrderRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabOrderResponse;
import sba.group3.backendmvc.entity.appointment.QueueStatus;
import sba.group3.backendmvc.entity.examination.*;
import sba.group3.backendmvc.entity.laboratory.LabOrder;
import sba.group3.backendmvc.entity.laboratory.LabStatus;
import sba.group3.backendmvc.entity.laboratory.LabTest;
import sba.group3.backendmvc.entity.laboratory.LabTestResult;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.entity.organization.RoomType;
import sba.group3.backendmvc.entity.staff.StaffSchedule;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.appointment.QueueTicketMapper;
import sba.group3.backendmvc.mapper.laboratory.LabOrderMapper;
import sba.group3.backendmvc.publisher.LabOrderEventPublisher;
import sba.group3.backendmvc.publisher.QueueEventPublisher;
import sba.group3.backendmvc.repository.appointment.QueueTicketRepository;
import sba.group3.backendmvc.repository.laboratory.LabOrderRepository;
import sba.group3.backendmvc.repository.laboratory.LabTestRepository;
import sba.group3.backendmvc.repository.patient.ExaminationRepository;
import sba.group3.backendmvc.repository.patient.PatientRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.repository.staff.StaffScheduleRepository;
import sba.group3.backendmvc.service.examination.ExaminationService;
import sba.group3.backendmvc.service.laboratory.LabOrderService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class LabOrderServiceImpl implements LabOrderService {
    private final LabOrderRepository labOrderRepository;
    private final LabOrderMapper labOrderMapper;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    private final ExaminationRepository examinationRepository;
    private final LabTestRepository labTestRepository;
    private final StaffScheduleRepository staffScheduleRepository;
    private final LabOrderEventPublisher labOrderEventPublisher;
    private final ExaminationService examinationService;
    private final QueueTicketRepository queueTicketRepository;
    private final QueueEventPublisher queueEventPublisher;
    private final QueueTicketMapper queueTicketMapper;

    @Override
    public Page<LabOrderResponse> filter(SearchFilter filter) {
        return labOrderRepository.search(filter).map(labOrderMapper::toDto1);
    }

    @Override
    public LabOrderResponse create(LabOrderRequest labOrderRequest) {
        var entity = labOrderMapper.toEntity(labOrderRequest);
        entity.setPatient(patientRepository.getReferenceById(labOrderRequest.patientId()));
        entity.setRequestedBy(staffRepository.getReferenceById(labOrderRequest.requestedById()));
        entity.setExamination(examinationRepository.getReferenceById(labOrderRequest.examinationId()));
        entity = labOrderRepository.save(entity);
        return labOrderMapper.toDto1(entity);
    }

    @Override
    public LabOrderResponse getById(UUID orderId) {
        var entity = labOrderRepository.findById(orderId).orElseThrow();
        return labOrderMapper.toDto1(entity);
    }

    @Transactional
    @Override
    public LabOrderResponse update(UUID orderId, LabOrderRequest labOrderRequest) {
        var entity = labOrderRepository.findById(orderId).orElseThrow();
        labOrderMapper.partialUpdate(labOrderRequest, entity);
        labOrderRepository.save(entity);
        var dto = labOrderMapper.toDto1(entity);
        labOrderEventPublisher.publishNewOrder(dto);
        examinationService.handleOrderStatusChanged(entity.getExamination().getId());
        return labOrderMapper.toDto1(entity);
    }

    @Override
    public void delete(UUID orderId) {
        var entity = labOrderRepository.findById(orderId).orElseThrow();
        entity.setDeleted(true);
        labOrderRepository.save(entity);
    }

    @Override
    public List<LabOrderResponse> createOrder(String examinationId, List<UUID> labTestIds) {
        Examination exam = examinationRepository.findById(UUID.fromString(examinationId))
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        if (exam.getStatus() != ExaminationStatus.ONGOING) {
            throw new AppException(ErrorCode.BAD_REQUEST, "Examination is not ongoing");
        }

        List<LabTest> services = labTestRepository.findAllById(labTestIds);

        Map<RoomType, List<LabTest>> grouped =
                services.stream().collect(Collectors.groupingBy(LabTest::getRoomType));

        List<LabOrder> orders = new ArrayList<>();

        for (var entry : grouped.entrySet()) {
            RoomType type = entry.getKey();
            List<LabTest> list = entry.getValue();
            StaffSchedule staffSchedule = pickLeastBusySchedule(type);
            Room room = staffSchedule.getRoom();
            LabOrder order = LabOrder.builder()
                    .examination(exam)
                    .patient(exam.getPatient())
                    .requestedBy(exam.getStaff())
                    .orderCode(generateCode())
                    .room(room)
                    .assignedStaff(staffSchedule.getStaff())
                    .status(LabStatus.PENDING)
                    .build();

            for (LabTest labTest : list) {
                order.getResults().add(
                        LabTestResult.builder()
                                .labOrder(order)
                                .labTest(labTest)
                                .status(LabStatus.PENDING)
                                .referenceRange(labTest.getReferenceRange())
                                .unit(labTest.getUnit())
                                .remark(labTest.getDescription())
                                .build()
                );
            }
            labOrderRepository.save(order);
            orders.add(order);
            labOrderEventPublisher.publishNewOrder(labOrderMapper.toDto1(order));
        }
        var ticket = exam.getQueueTicket();
        ticket.setStatus(QueueStatus.IN_SERVICE_WAITING_RESULT);
        queueTicketRepository.save(ticket);

        queueEventPublisher.publish(queueTicketMapper.toDto(ticket));
        return orders.stream()
                .map(labOrderMapper::toDto1)
                .toList();
    }

    @Override
    public List<LabOrderResponse> filterList(SearchFilter filter) {
        return labOrderRepository.searchAll(filter).stream()
                .map(labOrderMapper::toDto1)
                .toList();
    }

    private StaffSchedule pickLeastBusySchedule(RoomType roomType) {
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();

        List<StaffSchedule> schedules =
                staffScheduleRepository.findAvailableStaffForRoomType(roomType, today, now);

        if (schedules.isEmpty()) {
            throw new AppException(ErrorCode.NOT_FOUND,
                    "Không có nhân viên trực cho loại phòng: " + roomType);
        }

        List<UUID> roomIds = schedules.stream()
                .map(ss -> ss.getRoom().getId())
                .distinct()
                .toList();

        var countList = labOrderRepository.countActiveOrdersByRoomIds(roomIds);

        Map<UUID, Long> roomOrderCount = new HashMap<>();
        countList.forEach(row -> {
            UUID roomId = (UUID) row.get("roomId");
            Long count = (Long) row.get("activeOrders");
            roomOrderCount.put(roomId, count);
        });

        for (UUID roomId : roomIds) {
            roomOrderCount.putIfAbsent(roomId, 0L);
        }

        UUID chosenRoomId = roomOrderCount.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElseThrow()
                .getKey();

        return schedules.stream()
                .filter(s -> s.getRoom().getId().equals(chosenRoomId))
                .findFirst()
                .orElseThrow();
    }

    private String generateCode() {
        var counter = labOrderRepository.count() + 1;
        String countString = String.format("%06d", counter);
        return "LO-" + countString;
    }


}
