package sba.group3.backendmvc.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import sba.group3.backendmvc.enums.OAuthProvider;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class OAuthLoginRequest {
    @NotNull(message = "OAuth provider is required")
    OAuthProvider provider;
    @NotBlank(message = "Access token is required")
    String accessToken;
    @NotBlank(message = "Device ID is required")
    String deviceId;

    @Builder.Default
    Boolean rememberMe = true;

    String deviceName;
}
