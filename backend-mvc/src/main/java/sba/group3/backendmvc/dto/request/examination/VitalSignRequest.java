package sba.group3.backendmvc.dto.request.examination;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.VitalSign}
 */
public record VitalSignRequest(UUID examinationId, Double temperature, String bloodPressure, Integer pulse,
                               Integer respirationRate, Double height, Double weight) implements Serializable {
}