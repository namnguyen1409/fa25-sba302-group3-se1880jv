package sba.group3.backendmvc.dto.response.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sba.group3.backendmvc.enums.MfaType;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthResponse {
    boolean requires2FA;
    List<MfaType> mfaTypes;
    MfaType defaultMfaType;
    UUID challengeId;
    String tokenType;
    String accessToken;
    Instant expiresIn;
}
