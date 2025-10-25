package sba.group3.backendmvc.dto.request.auth;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class PasskeyLoginRequest {
    String username;
    String credential;
    String deviceId;
    @Builder.Default
    Boolean rememberMe = false;
}
