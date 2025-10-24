package sba.group3.backendmvc.service.auth.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.SignatureAlgorithm;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.entity.user.Permission;
import sba.group3.backendmvc.entity.user.Role;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.service.auth.JwtService;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JwtServiceImpl implements JwtService {

    final JwtEncoder jwtEncoder;
    @Value("${security.jwt.access-token.expiration}")
    Duration accessTokenExpiration;
    @Value("${security.jwt.refresh-token.expiration}")
    Duration refreshTokenExpiration;
    @Value("${security.jwt.token.issuer}")
    String issuer;
    @Value("${security.jwt.token.audience[0]}")
    List<String> audience;

    @Override
    public String generateAccessToken(User user, String jwtId, String deviceId) {
        return generateAccessToken(user, jwtId, deviceId, Instant.now().plus(accessTokenExpiration));
    }

    @Override
    public String generateAccessToken(User user, String jwtId, String deviceId, Instant customExpiration) {
        var scope = buildScope(user);
        var header = JwsHeader.with(SignatureAlgorithm.RS256)
                .type("JWT")
                .build();
        var claims = JwtClaimsSet.builder()
                .id(jwtId)
                .subject(user.getId().toString())
                .issuer(issuer)
                .audience(audience)
                .issuedAt(Instant.now())
                .expiresAt(customExpiration)
                .claim("scope", scope)
                .claim("deviceId", deviceId)
                .notBefore(Instant.now().minusSeconds(5))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }

    @Override
    public String generateRefreshToken(User user, String jwtId, String deviceId) {
        return generateRefreshToken(user, jwtId, deviceId, Instant.now().plus(refreshTokenExpiration));
    }

    @Override
    public String generateRefreshToken(User user, String jwtId, String deviceId, Instant customExpiration) {
        var header = JwsHeader.with(SignatureAlgorithm.RS256)
                .type("JWT")
                .build();
        var claims = JwtClaimsSet.builder()
                .id(jwtId)
                .subject(user.getId().toString())
                .issuer(issuer)
                .audience(audience)
                .issuedAt(Instant.now())
                .expiresAt(customExpiration)
                .claim("deviceId", deviceId)
                .notBefore(Instant.now().minusSeconds(5))
                .build();
        return jwtEncoder.encode(JwtEncoderParameters.from(header, claims)).getTokenValue();
    }

    private String buildScope(User user) {
        return Stream.concat(
                user.getRoles().stream().map(Role::getName),
                user.getRoles().stream()
                        .flatMap(role -> role.getPermissions().stream())
                        .map(Permission::getName)
        ).distinct().collect(Collectors.joining(" "));
    }

}
