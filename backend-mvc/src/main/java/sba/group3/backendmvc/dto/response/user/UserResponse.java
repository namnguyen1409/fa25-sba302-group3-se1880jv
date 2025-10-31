package sba.group3.backendmvc.dto.response.user;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.user.User}
 */
public record UserResponse(UUID id, String username, String email, boolean active, boolean locked, boolean mfaEnabled,
                           SimpleProfileResponse userProfile, boolean firstLogin,
                           Set<RoleNameResponse> roles) implements Serializable {
}