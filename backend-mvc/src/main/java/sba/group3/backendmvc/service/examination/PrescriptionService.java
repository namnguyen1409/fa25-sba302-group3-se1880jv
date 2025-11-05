package sba.group3.backendmvc.service.examination;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.PrescriptionRequest;
import sba.group3.backendmvc.dto.response.examination.PrescriptionResponse;


public interface PrescriptionService {
    Page<PrescriptionResponse> filter(SearchFilter filter);

    PrescriptionResponse create(PrescriptionRequest request);

    PrescriptionResponse update(String id, PrescriptionRequest request);

    void delete(String id);

    PrescriptionResponse getPrescriptionByExaminationId(String examinationId);
}
