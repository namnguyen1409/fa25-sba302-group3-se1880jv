package sba.group3.backendmvc.service.patient;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.patient.AllergyRequest;
import sba.group3.backendmvc.dto.response.patient.AllergyResponse;

import java.util.UUID;

public interface AllergiesService {
    Page<AllergyResponse> filter(SearchFilter filter);

    AllergyResponse create(UUID patientId, AllergyRequest request);

    AllergyResponse update(UUID allergyId, AllergyRequest request);

    void delete(UUID allergyId);
}
