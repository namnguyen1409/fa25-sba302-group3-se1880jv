package sba.group3.backendmvc.service.laboratory;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabTestResultRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResultResponse;

import java.util.UUID;

public interface LabTestResultService {
    Page<LabTestResultResponse> filter(SearchFilter filter);

    LabTestResultResponse create(UUID orderId, LabTestResultRequest labTestResultRequest);

    void deleteFromOrder(UUID orderId, UUID itemId);

    LabTestResultResponse update(UUID itemId, LabTestResultRequest labTestResultRequest);

    void verify(UUID itemId, UUID staffId);
}
