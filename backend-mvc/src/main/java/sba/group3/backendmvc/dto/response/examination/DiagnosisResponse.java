package sba.group3.backendmvc.dto.response.examination;

import sba.group3.backendmvc.dto.response.common.IcdCodeResponse;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.Diagnosis}
 */
public record DiagnosisResponse(UUID id, String note, IcdCodeResponse icdCode) implements Serializable {
}