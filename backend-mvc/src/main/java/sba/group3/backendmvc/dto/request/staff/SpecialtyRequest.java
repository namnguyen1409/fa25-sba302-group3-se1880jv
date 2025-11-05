package sba.group3.backendmvc.dto.request.staff;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.Specialty}
 */
public record SpecialtyRequest(UUID departmentId, String name, String description) implements Serializable {
}