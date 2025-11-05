package sba.group3.backendmvc.service.examination;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.DiagnosisRequest;
import sba.group3.backendmvc.dto.response.examination.DiagnosisResponse;

public interface DiagnosisService {
    Page<DiagnosisResponse> filter(SearchFilter filter);

    DiagnosisResponse create(DiagnosisRequest request);

    DiagnosisResponse update(String id, DiagnosisRequest request);

    void delete(String id);

    DiagnosisResponse searchByExaminationId(String examinationId);
}
