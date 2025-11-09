package sba.group3.backendmvc.service.examination.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderRequest;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderResponse;
import sba.group3.backendmvc.entity.appointment.QueueStatus;
import sba.group3.backendmvc.entity.examination.*;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.entity.organization.RoomType;
import sba.group3.backendmvc.entity.staff.StaffSchedule;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.appointment.QueueTicketMapper;
import sba.group3.backendmvc.mapper.examination.ServiceOrderMapper;
import sba.group3.backendmvc.publisher.OrderEventPublisher;
import sba.group3.backendmvc.publisher.QueueEventPublisher;
import sba.group3.backendmvc.repository.appointment.QueueTicketRepository;
import sba.group3.backendmvc.repository.examination.ServiceCatalogRepository;
import sba.group3.backendmvc.repository.examination.ServiceOrderRepository;
import sba.group3.backendmvc.repository.patient.ExaminationRepository;
import sba.group3.backendmvc.repository.staff.StaffScheduleRepository;
import sba.group3.backendmvc.service.examination.ExaminationService;
import sba.group3.backendmvc.service.examination.ServiceOrderService;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ServiceOrderServiceImpl implements ServiceOrderService {
    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderMapper serviceOrderMapper;
    private final ExaminationRepository examinationRepository;
    private final ServiceCatalogRepository serviceCatalogRepository;
    private final StaffScheduleRepository staffScheduleRepository;
    private final OrderEventPublisher orderEventPublisher;
    private final QueueTicketRepository queueTicketRepository;
    private final QueueEventPublisher queueEventPublisher;
    private final QueueTicketMapper queueTicketMapper;
    private final ExaminationService examinationService;

    @Override
    public Page<ServiceOrderResponse> filter(SearchFilter filter) {
        return serviceOrderRepository.search(filter).map(serviceOrderMapper::toDto1);
    }

    @Override
    public List<ServiceOrderResponse> filterList(SearchFilter filter) {
        return serviceOrderRepository.searchAll(filter).stream()
                .map(serviceOrderMapper::toDto1)
                .toList();
    }

    @Override
    public ServiceOrderResponse create(ServiceOrderRequest request) {
        ServiceOrder serviceOrder = serviceOrderMapper.toEntity(request);
        return serviceOrderMapper.toDto1(serviceOrderRepository.save(serviceOrder));
    }

    @Override
    @Transactional
    public ServiceOrderResponse update(String id, ServiceOrderRequest request) {

        ServiceOrder serviceOrder = serviceOrderRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new IllegalArgumentException("ServiceOrder not found"));

        serviceOrderMapper.partialUpdate(request, serviceOrder);

        ServiceOrder saved = serviceOrderRepository.save(serviceOrder);

        var dto = serviceOrderMapper.toDto1(saved);
        orderEventPublisher.publishNewOrder(dto);

        examinationService.handleOrderStatusChanged(saved.getExamination().getId());

        return dto;
    }


    @Override
    public void delete(String id) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new IllegalArgumentException("ServiceOrder with id " + id + " not found."));
        serviceOrder.setDeleted(true);
        serviceOrderRepository.save(serviceOrder);
    }

    @Override
    public ServiceOrderResponse getServiceOrdersByExaminationId(String examinationId) {
        Examination examination = examinationRepository.findById(UUID.fromString(examinationId)).orElseThrow(()->
                new IllegalArgumentException("Examination with id " + examinationId + " not found."));
        ServiceOrder serviceOrder = serviceOrderRepository.findByExamination((examination));
        if(serviceOrder == null) {
            throw new IllegalArgumentException("ServiceOrder for Examination id " + examinationId + " not found.");
        }

        return serviceOrderMapper.toDto1(serviceOrder);
    }

    @Override
    @Transactional
    public List<ServiceOrderResponse> createOrders(UUID examId, List<UUID> serviceIds) {
        Examination exam = examinationRepository.findById(examId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));
        if (exam.getStatus() != ExaminationStatus.ONGOING) {
            throw new AppException(ErrorCode.BAD_REQUEST, "Examination is not ongoing");
        }

        List<ServiceCatalog> services = serviceCatalogRepository.findAllById(serviceIds);
        List<ServiceOrder> orders = new ArrayList<>();
        List<ServiceCatalog> consultationServices = services.stream()
                .filter(s -> s.getRoomType() == RoomType.CONSULTATION)
                .toList();

        if (!consultationServices.isEmpty()) {

            ServiceOrder consultationOrder = ServiceOrder.builder()
                    .examination(exam)
                    .orderCode(generateCode())
                    .room(exam.getQueueTicket().getAssignedRoom())
                    .assignedStaff(exam.getStaff())
                    .status(ServiceOrderStatus.COMPLETED) // bác sĩ thực hiện ngay
                    .build();

            for (ServiceCatalog s : consultationServices) {
                consultationOrder.getItems().add(
                        ServiceOrderItem.builder()
                                .serviceOrder(consultationOrder)
                                .service(s)
                                .price(s.getPrice())
                                .build()
                );
            }

            serviceOrderRepository.save(consultationOrder);
            orders.add(consultationOrder);

            orderEventPublisher.publishNewOrder(serviceOrderMapper.toDto1(consultationOrder));
        }

        List<ServiceCatalog> routedServices = services.stream()
                .filter(s -> s.getRoomType() != RoomType.CONSULTATION)
                .toList();

        if (!routedServices.isEmpty()) {

            Map<RoomType, List<ServiceCatalog>> grouped =
                    routedServices.stream().collect(Collectors.groupingBy(ServiceCatalog::getRoomType));

            for (var entry : grouped.entrySet()) {

                RoomType type = entry.getKey();
                List<ServiceCatalog> list = entry.getValue();

                // chọn nhân viên ít bận nhất + phòng tương ứng
                StaffSchedule staffSchedule = pickLeastBusySchedule(type);
                Room room = staffSchedule.getRoom();

                ServiceOrder order = ServiceOrder.builder()
                        .examination(exam)
                        .orderCode(generateCode())
                        .room(room)
                        .assignedStaff(staffSchedule.getStaff())
                        .status(ServiceOrderStatus.PENDING)
                        .build();

                for (ServiceCatalog s : list) {
                    order.getItems().add(
                            ServiceOrderItem.builder()
                                    .serviceOrder(order)
                                    .service(s)
                                    .price(s.getPrice())
                                    .build()
                    );
                }

                serviceOrderRepository.save(order);
                orders.add(order);

                // publish event
                orderEventPublisher.publishNewOrder(serviceOrderMapper.toDto1(order));
            }

            var ticket = exam.getQueueTicket();
            ticket.setStatus(QueueStatus.IN_SERVICE_WAITING_RESULT);
            queueTicketRepository.save(ticket);

            queueEventPublisher.publish(queueTicketMapper.toDto(ticket));
        }
        return orders.stream()
                .map(serviceOrderMapper::toDto1)
                .toList();
    }


    @Override
    public ServiceOrderResponse getById(String id) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND,
                        "Service order with id " + id + " not found."));
        return serviceOrderMapper.toDto1(serviceOrder);
    }

    private String generateCode() {
        var counter = serviceOrderRepository.count() + 1;
        String countString = String.format("%06d", counter);
        return "SO-" + countString;
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

        var countList = serviceOrderRepository.countActiveOrdersByRoomIds(roomIds);

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

}
