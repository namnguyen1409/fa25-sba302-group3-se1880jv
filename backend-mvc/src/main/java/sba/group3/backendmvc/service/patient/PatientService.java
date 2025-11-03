package sba.group3.backendmvc.service.patient;


import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.patient.PatientRequest;
import sba.group3.backendmvc.dto.response.patient.PatientResponse;

import java.util.UUID;

public interface PatientService {
    Page<PatientResponse> filter(SearchFilter filter);

    PatientResponse getById(UUID patientId);

    Boolean existsByPhoneOrEmail(String phone, String email);

    PatientResponse create(PatientRequest request);

    PatientResponse update(UUID patientId, PatientRequest request);

    void delete(UUID patientId);
}
