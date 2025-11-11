package sba.group3.backendmvc.service.pharmacy.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.pharmacy.DispenseRecordRequest;
import sba.group3.backendmvc.dto.response.pharmacy.DispenseRecordResponse;
import sba.group3.backendmvc.entity.examination.Prescription;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.entity.organization.RoomType;
import sba.group3.backendmvc.entity.pharmacy.DispenseRecord;
import sba.group3.backendmvc.entity.pharmacy.DispenseStatus;
import sba.group3.backendmvc.entity.staff.StaffSchedule;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.pharmacy.DispenseRecordMapper;
import sba.group3.backendmvc.publisher.DispenseEventPublisher;
import sba.group3.backendmvc.repository.pharmacy.DispenseRecordRepository;
import sba.group3.backendmvc.repository.staff.StaffScheduleRepository;
import sba.group3.backendmvc.service.pharmacy.DispenseRecordService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class DispenseRecordServiceImpl implements DispenseRecordService {
    DispenseRecordRepository dispenseRecordRepository;
    DispenseRecordMapper dispenseRecordMapper;
    StaffScheduleRepository staffScheduleRepository;
    DispenseEventPublisher dispenseEventPublisher;

    @Override
    public Page<DispenseRecordResponse> filter(SearchFilter filter) {
        return dispenseRecordRepository.search(filter).map(dispenseRecordMapper::toDto);
    }

    @Override
    public DispenseRecordResponse getById(String recordId) {
        var dispenseRecord = dispenseRecordRepository.findById(UUID.fromString(recordId))
                .orElseThrow(() -> new RuntimeException("Dispense record not found"));
        return dispenseRecordMapper.toDto(dispenseRecord);
    }

    @Override
    public DispenseRecordResponse create(DispenseRecordRequest dispenseRecordRequest) {
        var dispenseRecord = dispenseRecordMapper.toEntity(dispenseRecordRequest);
        var savedRecord = dispenseRecordRepository.save(dispenseRecord);
        return dispenseRecordMapper.toDto(savedRecord);
    }

    @Override
    public DispenseRecordResponse update(UUID recordId, DispenseRecordRequest dispenseRecordRequest) {
        var existingRecord = dispenseRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Dispense record not found"));
        dispenseRecordMapper.partialUpdate(dispenseRecordRequest, existingRecord);
        var updatedRecord = dispenseRecordRepository.save(existingRecord);
        return dispenseRecordMapper.toDto(updatedRecord);
    }

    @Override
    public void delete(UUID recordId) {
        var entity = dispenseRecordRepository.findById(recordId)
                .orElseThrow(() -> new RuntimeException("Dispense record not found"));
        entity.setDeleted(true);
        dispenseRecordRepository.save(entity);

    }

    @Override
    @Transactional
    public DispenseRecord createDispenseRecord(Prescription prescription) {

        StaffSchedule pharmacySchedule = pickLeastBusySchedule(RoomType.PHARMACY);
        Room pharmacyRoom = pharmacySchedule.getRoom();

        BigDecimal totalCost = prescription.getItems().stream()
                .filter(item -> !item.getDeleted())
                .map(item -> item.getMedicine().getPrice()
                        .multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DispenseRecord record = DispenseRecord.builder()
                .prescription(prescription)
                .room(pharmacyRoom)
                .patient(prescription.getExamination().getPatient())
                .dispensedBy(pharmacySchedule.getStaff())
                .totalCost(totalCost)
                .status(DispenseStatus.PENDING)
                .build();
        dispenseEventPublisher.publishNewOrder(dispenseRecordMapper.toDto(record));

        return dispenseRecordRepository.save(record);
    }

    @Override
    public List<DispenseRecordResponse> filterList(SearchFilter filter) {
        return dispenseRecordRepository.searchAll(filter).stream().map(dispenseRecordMapper::toDto).toList();
    }

    public StaffSchedule pickLeastBusySchedule(RoomType roomType) {
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

        var countList = dispenseRecordRepository.countActiveOrdersByRoomIds(roomIds);

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
