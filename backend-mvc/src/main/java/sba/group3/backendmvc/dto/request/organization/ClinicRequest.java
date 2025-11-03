package sba.group3.backendmvc.dto.request.organization;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import sba.group3.backendmvc.dto.response.common.AddressResponse;

import java.io.Serializable;

/**
 * DTO for {@link sba.group3.backendmvc.entity.organization.Clinic}
 */
public record ClinicRequest(
        @NotNull @Size(max = 150)
        String name,
        String description,
        @NotNull(message = "Phone number is required")
        @Size(max = 15, message = "Phone number must not exceed 15 characters")
        @Pattern(
                regexp = "^(?:\\+84|0)(?:3[2-9]|5[2689]|7[06-9]|8[1-9]|9\\d)\\d{7}$",
                message = "Invalid Vietnamese phone number format"
        )
        String phone,
        AddressResponse address,
        @Size(max = 100, message = "Email must not exceed 100 characters")
        @Pattern(
                regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$",
                message = "Invalid email format"
        )
        String email
) implements Serializable {
}