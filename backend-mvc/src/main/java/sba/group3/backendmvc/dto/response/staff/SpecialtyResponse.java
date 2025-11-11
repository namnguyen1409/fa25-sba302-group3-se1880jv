package sba.group3.backendmvc.dto.response.staff;

import sba.group3.backendmvc.dto.response.organization.DepartmentResponse;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.Specialty}
 */
public record SpecialtyResponse(UUID id, String name, String description, DepartmentResponse department) implements Serializable {
}