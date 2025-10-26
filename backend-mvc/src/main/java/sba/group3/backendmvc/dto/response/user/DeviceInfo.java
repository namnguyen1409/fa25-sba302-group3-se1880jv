package sba.group3.backendmvc.dto.response.user;

import java.io.Serializable;
import java.time.Instant;

/**
 * DTO for {@link sba.group3.backendmvc.entity.user.DeviceSession}
 */
public record DeviceInfo(String deviceId, boolean trusted, Instant lastLoginAt) implements Serializable {
}