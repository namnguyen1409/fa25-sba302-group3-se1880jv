package sba.group3.backendmvc.dto.response.auth;

import sba.group3.backendmvc.enums.OAuthProvider;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link sba.group3.backendmvc.entity.auth.OAuthAccount}
 */
public record OAuthAccountResponse(Instant createdDate, OAuthProvider provider, String email, String name,
                                   String avatarUrl, boolean isRevoke) implements Serializable {
}