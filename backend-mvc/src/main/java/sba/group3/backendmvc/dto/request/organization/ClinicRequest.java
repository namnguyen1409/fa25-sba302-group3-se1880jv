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
        String phone,
        AddressResponse address,
        @Size(max = 100, message = "Email must not exceed 100 characters")
        String email,
        String taxCode, String website, String accountNumber, String bankName) implements Serializable {
}