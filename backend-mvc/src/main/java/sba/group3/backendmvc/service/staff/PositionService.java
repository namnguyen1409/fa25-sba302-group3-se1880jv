package sba.group3.backendmvc.service.staff;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.PositionRequest;
import sba.group3.backendmvc.dto.response.staff.PositionResponse;

import java.util.UUID;

public interface PositionService {
    Page<PositionResponse> filter(SearchFilter filter);

    PositionResponse create(PositionRequest positionRequest);

    PositionResponse update(UUID id, PositionRequest request);

    void delete(UUID id);

    PositionResponse getById(UUID id);
}
