package sba.group3.backendmvc.service.examination;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.VitalSignRequest;
import sba.group3.backendmvc.dto.response.examination.VitalSignResponse;

public interface VitalSignService {
    Page<VitalSignResponse> filter(SearchFilter filter);

    VitalSignResponse create(VitalSignRequest request);

    VitalSignResponse update(String id, VitalSignRequest request);

    void delete(String id);

    VitalSignResponse getVitalsByExaminationId(String examinationId);
}
