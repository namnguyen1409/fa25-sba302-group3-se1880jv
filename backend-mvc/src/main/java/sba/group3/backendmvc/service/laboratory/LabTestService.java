package sba.group3.backendmvc.service.laboratory;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabTestRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResponse;

import java.util.UUID;

public interface LabTestService {
    Page<LabTestResponse> filter(SearchFilter filter);

    LabTestResponse create(LabTestRequest labTestRequest);

    LabTestResponse update(UUID testId, LabTestRequest labTestRequest);

    void delete(UUID testId);

    LabTestResponse getById(UUID testId);
}
