package sba.group3.backendmvc.dto.request.auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MfaVerificationRequest {
    private String challengeId;
    private String otpCode;
    private String deviceId;
    private Boolean rememberMe;
}
