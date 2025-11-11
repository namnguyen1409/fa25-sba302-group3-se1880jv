package sba.group3.backendmvc.dto.request.examination;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.Diagnosis}
 */
public record DiagnosisRequest(
        UUID examinationId,
        String note,
        UUID icdCodeId
) implements Serializable {
}