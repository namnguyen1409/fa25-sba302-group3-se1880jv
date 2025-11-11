package sba.group3.backendmvc.service.staff;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.controller.staff.StaffController;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.StaffScheduleRequest;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleResponse;
import sba.group3.backendmvc.entity.staff.ScheduleStatus;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface StaffScheduleService {
    Page<StaffScheduleResponse> filter(SearchFilter filter);

    StaffScheduleResponse create(UUID staffId, StaffScheduleRequest request);

    StaffScheduleResponse update(UUID scheduleId, StaffScheduleRequest request);

    void delete(UUID scheduleId);

    List<StaffScheduleResponse> getByStaffAndRange(UUID staffId, LocalDate from, LocalDate to);

    void generate(UUID staffId, int daysAhead);                  // generate từ template
    StaffScheduleResponse markStatus(UUID scheduleId, ScheduleStatus status, String note);
    StaffScheduleResponse createDayOff(StaffController.StaffScheduleDayOffRequest req); // override OFF
}
