package sba.group3.backendmvc.dto.response.examination;

import sba.group3.backendmvc.dto.response.laboratory.LabOrderResponse;
import sba.group3.backendmvc.dto.response.patient.PatientResponse;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.entity.examination.ExaminationStatus;
import sba.group3.backendmvc.entity.examination.ExaminationType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.Examination}
 */
public record ExaminationResponse(UUID id, PatientResponse patient, StaffResponse staff, ExaminationType type,
                                  ExaminationStatus status, String symptom, String diagnosisSummary,
                                  LocalDateTime examinationDate, PrescriptionResponse prescription,
                                  Set<ServiceOrderResponse> serviceOrders, Set<VitalSignResponse> vitalSigns,
                                  Set<DiagnosisResponse> diagnoses,
                                  Set<LabOrderResponse> labOrders) implements Serializable {
}