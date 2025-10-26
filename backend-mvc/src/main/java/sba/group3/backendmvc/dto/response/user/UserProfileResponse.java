package sba.group3.backendmvc.dto.response.user;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import sba.group3.backendmvc.dto.response.common.AddressResponse;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.user.UserProfile}
 */
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
public class UserProfileResponse {
    UUID id;
    Instant createdDate;
    Instant lastModifiedDate;
    String userUsername;
    String userEmail;
    String userPhone;
    String fullName;
    LocalDate dateOfBirth;
    AddressResponse address;
    String avatarUrl;
}