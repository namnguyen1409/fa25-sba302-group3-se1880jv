package sba.group3.backendmvc.service.patient;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.patient.EmergencyContactRequest;
import sba.group3.backendmvc.dto.response.patient.EmergencyContactResponse;

import java.util.UUID;

public interface EmergencyService {
    Page<EmergencyContactResponse> filter(SearchFilter filter);

    EmergencyContactResponse create(UUID patientId, EmergencyContactRequest request);

    EmergencyContactResponse update(UUID contactId, EmergencyContactRequest request);

    void delete(UUID contactId);
}
