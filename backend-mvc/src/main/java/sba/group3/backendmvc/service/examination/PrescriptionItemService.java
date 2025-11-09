package sba.group3.backendmvc.service.examination;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.examination.PrescriptionItemRequest;
import sba.group3.backendmvc.dto.response.examination.PrescriptionItemResponse;

public interface PrescriptionItemService {
    Page<PrescriptionItemResponse> filter(SearchFilter filter);

    PrescriptionItemResponse create(PrescriptionItemRequest request);

    PrescriptionItemResponse update(String id, PrescriptionItemRequest request);

    void delete(String id);

    PrescriptionItemResponse createItem(String prescriptionId, PrescriptionItemRequest request);
}
