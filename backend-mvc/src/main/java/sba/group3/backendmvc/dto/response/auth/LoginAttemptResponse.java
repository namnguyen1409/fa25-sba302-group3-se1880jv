package sba.group3.backendmvc.dto.response.auth;

import sba.group3.backendmvc.enums.LoginStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.auth.LoginAttempt}
 */
public record LoginAttemptResponse(UUID id, Instant createdDate, String ipAddress, String userAgent, LoginStatus status,
                                   String loginMethod) implements Serializable {
}