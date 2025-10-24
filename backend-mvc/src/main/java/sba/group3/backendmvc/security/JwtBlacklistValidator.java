package sba.group3.backendmvc.security;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.service.auth.AuthTokenBlacklistService;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtBlacklistValidator implements OAuth2TokenValidator<Jwt> {
    private final AuthTokenBlacklistService authTokenBlacklistService;

    @Override
    public OAuth2TokenValidatorResult validate(Jwt token) {
        String jti = token.getId();
        if (authTokenBlacklistService.isBlackListed(jti)) {
            return OAuth2TokenValidatorResult.failure(
                    new OAuth2Error("invalid_token", "JWT expired", "JWT expired")
            );
        }
        return OAuth2TokenValidatorResult.success();
    }
}
