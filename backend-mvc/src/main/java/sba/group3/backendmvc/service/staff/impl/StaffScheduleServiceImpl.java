package sba.group3.backendmvc.service.staff.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.controller.staff.StaffController;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.StaffScheduleRequest;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleResponse;
import sba.group3.backendmvc.entity.organization.RoomType;
import sba.group3.backendmvc.entity.staff.ScheduleStatus;
import sba.group3.backendmvc.entity.staff.StaffSchedule;
import sba.group3.backendmvc.entity.staff.StaffScheduleTemplate;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.staff.StaffScheduleMapper;
import sba.group3.backendmvc.repository.organization.RoomRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.repository.staff.StaffScheduleRepository;
import sba.group3.backendmvc.repository.staff.StaffScheduleTemplateRepository;
import sba.group3.backendmvc.service.staff.StaffScheduleService;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffScheduleServiceImpl implements StaffScheduleService {

    private final StaffScheduleRepository staffScheduleRepository;
    private final StaffScheduleMapper staffScheduleMapper;
    private final StaffRepository staffRepository;
    private final RoomRepository roomRepository;
    private final StaffScheduleTemplateRepository staffScheduleTemplateRepository;

    @Override
    public Page<StaffScheduleResponse> filter(SearchFilter filter) {
        return staffScheduleRepository.search(filter).map(staffScheduleMapper::toDto1);
    }

    @Override
    public StaffScheduleResponse create(UUID staffId, StaffScheduleRequest request) {

        validateScheduleConstraints(
                staffId,
                null,
                request.date(),
                request.startTime(),
                request.endTime(),
                request.roomId()
        );

        var entity = staffScheduleMapper.toEntity(request);
        entity.setStaff(staffRepository.getReferenceById(staffId));
        entity.setRoom(roomRepository.getReferenceById(request.roomId()));

        var saved = staffScheduleRepository.save(entity);
        return staffScheduleMapper.toDto1(saved);
    }


    @Override
    public StaffScheduleResponse update(UUID scheduleId, StaffScheduleRequest request) {

        var entity = staffScheduleRepository.findById(scheduleId).orElseThrow();

        validateScheduleConstraints(
                entity.getStaff().getId(),
                scheduleId,
                request.date(),
                request.startTime(),
                request.endTime(),
                request.roomId()
        );

        staffScheduleMapper.partialUpdate(request, entity);

        if (request.roomId() != null) {
            entity.setRoom(roomRepository.getReferenceById(request.roomId()));
        }

        var updated = staffScheduleRepository.save(entity);
        return staffScheduleMapper.toDto1(updated);
    }


    @Override
    public void delete(UUID scheduleId) {
        var entity = staffScheduleRepository.findById(scheduleId).orElseThrow();
        entity.setDeleted(true);
        staffScheduleRepository.save(entity);
    }


    private void validateScheduleConstraints(
            UUID staffId,
            UUID scheduleId, // null khi create
            LocalDate date,
            LocalTime start,
            LocalTime end,
            UUID roomId
    ) {

        if (start.isAfter(end) || start.equals(end)) {
            throw new AppException(ErrorCode.SCHEDULE_TIME_INVALID);
        }

        // Lấy tất cả lịch cùng ngày
        var sameDaySchedules = staffScheduleRepository
                .findAllByStaffIdAndDateAndDeletedFalse(staffId, date);

        for (var s : sameDaySchedules) {
            // Bỏ qua lịch hiện tại khi update
            if (s.getId().equals(scheduleId)) continue;

            // Rule 1: Overlap time
            boolean overlap = start.isBefore(s.getEndTime()) && end.isAfter(s.getStartTime());
            if (overlap) {
                throw new AppException(ErrorCode.SCHEDULE_TIME_OVERLAP);
            }

            // Rule 2: Room conflict
            if (s.getRoom() != null && s.getRoom().getId().equals(roomId)) {
                boolean roomOverlap = start.isBefore(s.getEndTime()) && end.isAfter(s.getStartTime());
                if (roomOverlap) {
                    throw new AppException(ErrorCode.SCHEDULE_ROOM_CONFLICT);
                }
            }
        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<StaffScheduleResponse> getByStaffAndRange(UUID staffId, LocalDate from, LocalDate to) {
        var list = staffScheduleRepository.findByStaffAndRange(staffId, from, to);
        return list.stream().map(staffScheduleMapper::toDto1).toList();
    }

    @Override
    @Transactional
    public void generate(UUID staffId, int daysAhead) {
        int days = daysAhead <= 0 ? 30 : daysAhead;
        LocalDate start = LocalDate.now();
        LocalDate end = start.plusDays(days);

        // Xóa lịch AVAILABLE tương lai
        staffScheduleRepository.deleteAllByStaff_IdAndDateGreaterThanEqualAndStatus(
                staffId, start, ScheduleStatus.AVAILABLE
        );

        // Lấy template active
        List<StaffScheduleTemplate> templates =
                staffScheduleTemplateRepository.findByStaff_IdAndActiveTrue(staffId);

        Map<DayOfWeek, List<StaffScheduleTemplate>> byDow =
                templates.stream().collect(Collectors.groupingBy(StaffScheduleTemplate::getDayOfWeek));

        for (LocalDate d = start; !d.isAfter(end); d = d.plusDays(1)) {

            // ✅ Skip ngày đã từng bị admin chỉnh sửa (CHANGED/OFF/CANCELLED)
            boolean hasManualChange =
                    staffScheduleRepository.existsByStaff_IdAndDateAndStatusNot(
                            staffId, d, ScheduleStatus.AVAILABLE
                    );

            if (hasManualChange) continue;

            var list = byDow.getOrDefault(d.getDayOfWeek(), List.of());

            for (var tpl : list) {

                // ✅ Skip nếu trùng hẳn slot cũ
                boolean exists = staffScheduleRepository.existsByStaff_IdAndDateAndRoom_IdAndStartTimeAndEndTime(
                        staffId, d, tpl.getRoom().getId(), tpl.getStartTime(), tpl.getEndTime()
                );
                if (exists) continue;

                // ✅ Skip nếu trùng / overlap với slot CHANGED/OFF/etc
                boolean overlapWithChanged =
                        staffScheduleRepository.existsOverlapIgnoringAvailable(
                                staffId, d, tpl.getStartTime(), tpl.getEndTime()
                        );
                if (overlapWithChanged) continue;

                // ✅ Tạo slot mới
                StaffSchedule sched = StaffSchedule.builder()
                        .staff(tpl.getStaff())
                        .date(d)
                        .startTime(tpl.getStartTime())
                        .endTime(tpl.getEndTime())
                        .room(tpl.getRoom())
                        .status(ScheduleStatus.AVAILABLE)
                        .build();

                staffScheduleRepository.save(sched);
            }
        }
    }



    @Override
    public StaffScheduleResponse markStatus(UUID scheduleId, ScheduleStatus status, String note) {
        var s = staffScheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new AppException(ErrorCode.SCHEDULE_NOT_FOUND));
        // TODO: nếu có Appointment CONFIRMED trong slot này, chặn hoặc yêu cầu reassign
        s.setStatus(status);
        s.setNote(note);
        staffScheduleRepository.save(s);
        return staffScheduleMapper.toDto1(s);
    }

    @Override
    public StaffScheduleResponse createDayOff(StaffController.StaffScheduleDayOffRequest req) {
        var staff = staffRepository.findById(req.staffId())
                .orElseThrow(() -> new AppException(ErrorCode.STAFF_NOT_FOUND));
        var room = roomRepository.findById(req.roomId())
                .orElseThrow(() -> new AppException(ErrorCode.ROOM_NOT_FOUND));

        // tìm slot nếu đã generate
        Optional<StaffSchedule> maybe = staffScheduleRepository.findByStaffAndRange(req.staffId(), req.date(), req.date()).stream()
                .filter(s -> s.getRoom().getId().equals(req.roomId())
                        && s.getStartTime().equals(req.startTime())
                        && s.getEndTime().equals(req.endTime()))
                .findFirst();

        StaffSchedule target = maybe.orElseGet(() ->
                StaffSchedule.builder()
                        .staff(staff)
                        .date(req.date())
                        .startTime(req.startTime())
                        .endTime(req.endTime())
                        .room(room)
                        .status(ScheduleStatus.AVAILABLE)
                        .build()
        );

        target.setStatus(ScheduleStatus.OFF);
        target.setNote(req.reason());
        staffScheduleRepository.save(target);
        return staffScheduleMapper.toDto1(target);
    }




}
