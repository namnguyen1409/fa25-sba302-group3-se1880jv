package sba.group3.backendmvc.dto.request.user;

import sba.group3.backendmvc.dto.response.common.AddressResponse;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link sba.group3.backendmvc.entity.user.UserProfile}
 */
public record UserProfileRequest(
        String fullName,
        LocalDate dateOfBirth,
        AddressResponse address,
        String phone,
        String avatarUrl) implements Serializable {
}