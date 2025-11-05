package sba.group3.backendmvc.service.staff;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.StaffRequest;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface StaffService {
    Page<StaffResponse> filter(SearchFilter filter);

    StaffResponse create(StaffRequest request);

    StaffResponse update(UUID id, StaffRequest request);

    void delete(UUID id);

    StaffResponse getById(UUID id);

    List<StaffResponse> findDoctorsBySpecialty(UUID specialtyId);

    List<StaffResponse> findAvailableDoctors(UUID specialtyId, DayOfWeek day, LocalTime time);

    StaffResponse autoPickDoctor(UUID specialtyId);
}
