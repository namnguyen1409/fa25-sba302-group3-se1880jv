package sba.group3.backendmvc.dto.response.patient;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.patient.EmergencyContact}
 */
public record EmergencyContactResponse(UUID id, String fullName, String relationship,
                                       String phone) implements Serializable {
}