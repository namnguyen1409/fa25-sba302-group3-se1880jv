package sba.group3.backendmvc.dto.request.auth;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.UUID;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasskeyRegistrationRequest {
    private UUID userId;
    private String username;
    private String displayName;
    private String credential;
}
