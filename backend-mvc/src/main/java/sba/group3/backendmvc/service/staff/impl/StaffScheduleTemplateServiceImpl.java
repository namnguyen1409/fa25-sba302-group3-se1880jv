package sba.group3.backendmvc.service.staff.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.StaffScheduleTemplateRequest;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleTemplateResponse;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.staff.StaffScheduleTemplateMapper;
import sba.group3.backendmvc.repository.organization.RoomRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.repository.staff.StaffScheduleTemplateRepository;
import sba.group3.backendmvc.service.staff.StaffScheduleTemplateService;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class StaffScheduleTemplateServiceImpl implements StaffScheduleTemplateService {
    private final StaffScheduleTemplateRepository staffScheduleTemplateRepository;
    private final StaffScheduleTemplateMapper staffScheduleTemplateMapper;
    private final StaffRepository staffRepository;
    private final RoomRepository roomRepository;

    @Override
    public Page<StaffScheduleTemplateResponse> filter(SearchFilter filter) {
        return staffScheduleTemplateRepository.search(filter).map(staffScheduleTemplateMapper::toDto1);
    }

    @Override
    public StaffScheduleTemplateResponse create(UUID staffId, StaffScheduleTemplateRequest request) {
        var staff = staffRepository.getReferenceById(staffId);

        validateTemplateConstraints(
                staffId,
                null,               // create = no existing ID
                request.dayOfWeek(),
                request.startTime(),
                request.endTime(),
                request.roomId()
        );

        var entity = staffScheduleTemplateMapper.toEntity(request);
        entity.setStaff(staff);
        entity.setRoom(roomRepository.getReferenceById(request.roomId()));

        var saved = staffScheduleTemplateRepository.save(entity);
        return staffScheduleTemplateMapper.toDto1(saved);
    }


    @Override
    public StaffScheduleTemplateResponse update(UUID templateId, StaffScheduleTemplateRequest request) {
        var entity = staffScheduleTemplateRepository.findById(templateId)
                .orElseThrow(() -> new AppException(ErrorCode.NOT_FOUND));

        validateTemplateConstraints(
                entity.getStaff().getId(),
                templateId,                         // ignore this template when validating
                request.dayOfWeek(),
                request.startTime(),
                request.endTime(),
                request.roomId()
        );

        // Update fields
        staffScheduleTemplateMapper.partialUpdate(request, entity);

        if (request.roomId() != null) {
            entity.setRoom(roomRepository.getReferenceById(request.roomId()));
        }

        var saved = staffScheduleTemplateRepository.save(entity);
        return staffScheduleTemplateMapper.toDto1(saved);
    }

    private void validateTemplateConstraints(
            UUID staffId,
            UUID currentId,               // null when create
            DayOfWeek dayOfWeek,
            LocalTime start,
            LocalTime end,
            UUID roomId
    ) {

        // 0. Validate: start < end
        if (!start.isBefore(end)) {
            throw new AppException(ErrorCode.SCHEDULE_TIME_INVALID);
        }

        // 1. Check duplicate weekday for same staff
        var sameDayList = staffScheduleTemplateRepository
                .findAllByStaffIdAndDayOfWeekAndDeletedFalse(staffId, dayOfWeek);

        for (var t : sameDayList) {

            // Skip itself when updating
            if (t.getId().equals(currentId)) continue;

            // Rule: Only 1 template per day per staff
            if (t.getDayOfWeek() == dayOfWeek) {
                throw new AppException(ErrorCode.SCHEDULE_TEMPLATE_DUPLICATE_DAY);
            }

            // Rule: Time overlap in same staff
            boolean overlap = start.isBefore(t.getEndTime()) && end.isAfter(t.getStartTime());
            if (overlap) {
                throw new AppException(ErrorCode.SCHEDULE_TIME_OVERLAP);
            }
        }


        // 2. Check room conflict with OTHER staff
        var roomConflicts = staffScheduleTemplateRepository
                .findAllByRoomIdAndDayOfWeekAndDeletedFalse(roomId, dayOfWeek);

        for (var t : roomConflicts) {

            // Skip itself when updating
            if (t.getId().equals(currentId)) continue;

            // Skip if it’s the same doctor
            if (t.getStaff().getId().equals(staffId)) continue;

            // Rule: No two staff in same room at same time
            boolean overlap = start.isBefore(t.getEndTime()) && end.isAfter(t.getStartTime());
            if (overlap) {
                throw new AppException(ErrorCode.SCHEDULE_ROOM_CONFLICT);
            }
        }
    }



    @Override
    public void delete(UUID templateId) {
        var entity = staffScheduleTemplateRepository.findById(templateId).orElseThrow();
        entity.setDeleted(true);
        staffScheduleTemplateRepository.save(entity);
    }
}
