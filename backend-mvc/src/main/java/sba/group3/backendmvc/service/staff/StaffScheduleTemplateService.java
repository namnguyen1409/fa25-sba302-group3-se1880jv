package sba.group3.backendmvc.service.staff;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleTemplateResponse;

import java.util.UUID;

public interface StaffScheduleTemplateService {
    Page<StaffScheduleTemplateResponse> filter(SearchFilter filter);

    StaffScheduleTemplateResponse create(UUID staffId, StaffScheduleTemplateResponse request);

    StaffScheduleTemplateResponse update(UUID templateId, StaffScheduleTemplateResponse request);

    void delete(UUID templateId);
}
