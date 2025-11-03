package sba.group3.backendmvc.service.staff;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleResponse;

import java.util.UUID;

public interface StaffScheduleService {
    Page<StaffScheduleResponse> filter(SearchFilter filter);

    StaffScheduleResponse create(UUID staffId, StaffScheduleResponse request);

    StaffScheduleResponse update(UUID scheduleId, StaffScheduleResponse request);

    void delete(UUID scheduleId);
}
