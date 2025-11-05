package sba.group3.backendmvc.dto.request.organization;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.organization.Department}
 */
public record DepartmentRequest(
        @NotBlank(message = "Name can not blank")
        String name,
        @Size(max = 500, message = "Description cannot exceed 500 characters")
        String description,
        @NotNull(message = "Clinic ID is required")
        UUID clinicId) implements Serializable {
}