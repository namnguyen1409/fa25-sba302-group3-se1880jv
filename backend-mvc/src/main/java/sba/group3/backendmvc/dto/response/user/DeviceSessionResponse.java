package sba.group3.backendmvc.dto.response.user;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.user.DeviceSession}
 */
public record DeviceSessionResponse(UUID id, String createdBy, String deviceName, String ipAddress, String userAgent,
                                    boolean trusted, Instant expiresIn, boolean revoked, boolean rememberMe,
                                    Instant lastLoginAt) implements Serializable {
}