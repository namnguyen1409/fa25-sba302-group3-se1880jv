package sba.group3.backendmvc.dto.request.organization;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link sba.group3.backendmvc.entity.organization.Department}
 */
public record DepartmentRequest(
        @NotBlank(message = "Name can not blank")
        String name,
        String description) implements Serializable {
}