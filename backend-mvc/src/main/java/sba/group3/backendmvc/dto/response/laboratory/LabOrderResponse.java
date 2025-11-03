package sba.group3.backendmvc.dto.response.laboratory;

import sba.group3.backendmvc.dto.response.patient.PatientResponse;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.entity.laboratory.LabStatus;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.laboratory.LabOrder}
 */
public record LabOrderResponse(UUID id, PatientResponse patient, StaffResponse requestedBy, UUID examinationId,
                               LabStatus status, String orderCode,
                               Set<LabTestResultResponse> results) implements Serializable {
}