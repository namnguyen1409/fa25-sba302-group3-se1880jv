package sba.group3.backendmvc.dto.request.examination;

import sba.group3.backendmvc.entity.examination.ExaminationType;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.Examination}
 */
public record ExaminationRequest(UUID patientId, UUID staffId, ExaminationType type, String symptom,
                                 String diagnosisSummary) implements Serializable {
}