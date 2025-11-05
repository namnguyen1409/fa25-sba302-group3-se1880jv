package sba.group3.backendmvc.dto.response.examination;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.Diagnosis}
 */
public record DiagnosisResponse(UUID id, String icdCode, String diseaseName, String note) implements Serializable {
}