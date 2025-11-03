package sba.group3.backendmvc.service.staff;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.PositionRequest;
import sba.group3.backendmvc.dto.response.staff.PositionResponse;

public interface PositionService {
    Page<PositionResponse> filter(SearchFilter filter);

    PositionResponse create(PositionRequest positionRequest);

    PositionResponse update(String id, PositionRequest request);

    void delete(String id);
}
