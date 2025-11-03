package sba.group3.backendmvc.dto.response.organization;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.organization.Clinic}
 */
public record ClinicResponse(
        UUID id, String name, String description, String phone,
        String email) implements Serializable {
}