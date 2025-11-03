package sba.group3.backendmvc.service.staff;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.SpecialtyRequest;
import sba.group3.backendmvc.dto.response.staff.PositionResponse;
import sba.group3.backendmvc.dto.response.staff.SpecialtyResponse;

import java.util.UUID;

public interface SpecialtyService {
    Page<SpecialtyResponse> filter(SearchFilter filter);

    SpecialtyResponse create(SpecialtyRequest request);

    SpecialtyResponse update(UUID id, SpecialtyRequest request);

    void delete(UUID id);

    SpecialtyResponse getById(UUID id);
}
