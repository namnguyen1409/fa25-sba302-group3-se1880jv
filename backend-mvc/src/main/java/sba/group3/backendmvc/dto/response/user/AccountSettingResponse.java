package sba.group3.backendmvc.dto.response.user;

import sba.group3.backendmvc.dto.response.auth.OAuthAccountResponse;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link sba.group3.backendmvc.entity.user.User}
 */
public record AccountSettingResponse(String username, String email, boolean active, boolean mfaEnabled,
                                     boolean firstLogin,
                                     Set<OAuthAccountResponse> OAuthAccounts) implements Serializable {
}