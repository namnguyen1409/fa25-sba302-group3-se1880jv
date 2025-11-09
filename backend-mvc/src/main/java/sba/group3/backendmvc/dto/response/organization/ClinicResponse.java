package sba.group3.backendmvc.dto.response.organization;

import sba.group3.backendmvc.dto.response.common.AddressResponse;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.organization.Clinic}
 */
public record ClinicResponse(
        UUID id,
        String name,
        String description,
        String phone,
        String email,
        AddressResponse address,
        List<DepartmentSimpleResponse> departments,
        String taxCode, String website, String accountNumber, String bankName) implements Serializable {
}