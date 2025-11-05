package sba.group3.backendmvc.dto.response.patient;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.patient.Allergy}
 */
public record AllergyResponse(UUID id, String substance, String reaction, String severity) implements Serializable {
}