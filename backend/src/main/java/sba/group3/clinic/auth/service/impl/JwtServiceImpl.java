package sba.group3.clinic.auth.service.impl;

import io.jsonwebtoken.security.Keys;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import sba.group3.clinic.auth.repository.TokenBlacklistRepository;
import sba.group3.clinic.auth.security.CustomUserDetails;
import sba.group3.clinic.auth.service.JwtService;
import io.jsonwebtoken.Jwts;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class JwtServiceImpl implements JwtService {

    private final TokenBlacklistRepository tokenBlacklistRepository;
    @NonFinal
    @Value("${security.jwt.secret}")
    private String secretKey;

    @NonFinal
    @Value("${security.jwt.expiration}")
    private long expiration;

    @Override
    public String generateAccessToken(CustomUserDetails userDetails) {

        var roles = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .toList();

        return Jwts.builder()
                .header()
                .type("access_token")
                .and()
                .id(UUID.randomUUID().toString())
                .subject(userDetails.getUser().getId().toString())
                .claim("roles", roles)
                .issuer("group3")
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(30, ChronoUnit.MILLIS)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    @Override
    public String generateRefreshToken(CustomUserDetails userDetails) {
        return Jwts.builder()
                .header()
                .type("refresh_token")
                .and()
                .subject(userDetails.getUser().getId().toString())
                .issuer("group3")
                .issuedAt(Date.from(Instant.now()))
                .expiration(Date.from(Instant.now().plus(7, ChronoUnit.DAYS))) // 7 ngày
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    @Override
    public UUID getUserId(String token) {
        var claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return UUID.fromString(claims.getSubject());
    }

    @Override
    public String getJti(String token) {
        var claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getId();
    }

    @Override
    public boolean isValidToken(String token, CustomUserDetails userDetails) {
        var inBlackList = tokenBlacklistRepository.existsByJti(getJti(token));
        UUID userId = getUserId(token);
        var isExpire = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getExpiration()
                .before(new Date());

        return (!inBlackList && !isExpire && userId.equals(userDetails.getUser().getId()));
    }


}
