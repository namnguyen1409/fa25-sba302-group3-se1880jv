package sba.group3.backendmvc.dto.response.auth;

import sba.group3.backendmvc.enums.MfaType;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.auth.MfaConfig}
 */
public record MfaConfigResponse(UUID id, Instant createdDate, MfaType mfaType, String contact, Boolean primary,
                                Instant lastVerifiedAt, String deviceName, boolean revoked) implements Serializable {
}