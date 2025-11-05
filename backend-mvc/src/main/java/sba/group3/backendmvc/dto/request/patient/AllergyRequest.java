package sba.group3.backendmvc.dto.request.patient;

import jakarta.validation.constraints.NotBlank;

import java.io.Serializable;

/**
 * DTO for {@link sba.group3.backendmvc.entity.patient.Allergy}
 */
public record AllergyRequest(
        @NotBlank(message = "This field cannot be blank")
        String substance,
        @NotBlank(message = "This field cannot be blank")
        String reaction,
        @NotBlank(message = "This field cannot be blank")
        String severity)
        implements Serializable {
}