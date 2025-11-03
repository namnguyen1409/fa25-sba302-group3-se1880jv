package sba.group3.backendmvc.dto.response.examination;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.VitalSign}
 */
public record VitalSignResponse(UUID id, Double temperature, String bloodPressure, Integer pulse,
                                Integer respirationRate, Double height, Double weight) implements Serializable {
}