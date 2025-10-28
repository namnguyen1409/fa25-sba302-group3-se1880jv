package sba.group3.backendmvc.service.auth.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.entity.user.DeviceSession;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.repository.user.DeviceSessionRepository;
import sba.group3.backendmvc.service.auth.AuthTokenBlacklistService;
import sba.group3.backendmvc.service.auth.JwtService;
import sba.group3.backendmvc.service.auth.TokenIssuerService;
import sba.group3.backendmvc.service.infrastructure.CookieService;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TokenIssuerServiceImpl implements TokenIssuerService {

    DeviceSessionRepository deviceSessionRepository;
    JwtService jwtService;
    CookieService cookieService;
    private final AuthTokenBlacklistService authTokenBlacklistService;
    @NonFinal
    @Value("${security.jwt.refresh-token.expiration}")
    Duration refreshTokenExpiration;

    @NonFinal
    @Value("${security.jwt.access-token.expiration}")
    Duration accessTokenExpiration;

    // cấp token mới khi đăng nhập hoặc đăng ký thành công
    @Transactional
    @Override
    public AuthResponse issue(User user, DeviceSession deviceSession, String deviceId) {
        var accessTokenId = UUID.randomUUID().toString();
        var refreshTokenId = UUID.randomUUID().toString();

        deviceSession.setAccessTokenId(accessTokenId);
        deviceSession.setRefreshTokenId(refreshTokenId);

        var expiresIn = deviceSession.isRememberMe()
                ? Instant.now().plus(refreshTokenExpiration)
                : Instant.now().plus(1, ChronoUnit.DAYS);

        deviceSession.setExpiresIn(expiresIn);
        deviceSession.setLastLoginAt(Instant.now());
        deviceSession.setLastRefreshedAt(Instant.now());
        deviceSession.setRevoked(false);

        var accessToken = jwtService.generateAccessToken(user, accessTokenId, deviceId);
        var refreshToken = jwtService.generateRefreshToken(user, refreshTokenId, deviceId, expiresIn);

        cookieService.addRefreshTokenCookie(refreshToken, refreshTokenExpiration);

        return AuthResponse.builder()
                .requires2FA(false)
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(expiresIn)
                .build();
    }

    // cấp token mới khi token cũ hết hạn (refresh token) - không remember me thì vẫn giữ nguyên thời gian hết hạn cũ
    @Transactional
    @Override
    public AuthResponse refresh(User user, DeviceSession deviceSession, String deviceId) {
        var newAccessTokenId = UUID.randomUUID().toString();
        var newRefreshTokenId = UUID.randomUUID().toString();
        // revoke old tokens by replacing their IDs
        authTokenBlacklistService.addToBlackList(
                deviceSession.getAccessTokenId(),
                deviceSession.getLastRefreshedAt().plus(accessTokenExpiration),
                "Access token refreshed"
        );
        authTokenBlacklistService.addToBlackList(
                deviceSession.getRefreshTokenId(),
                deviceSession.getLastRefreshedAt().plus(refreshTokenExpiration),
                "Refresh token refreshed"
        );

        deviceSession.setAccessTokenId(newAccessTokenId);
        deviceSession.setRefreshTokenId(newRefreshTokenId);

        var expiresIn = deviceSession.isRememberMe()
                ? Instant.now().plus(refreshTokenExpiration)
                : deviceSession.getExpiresIn();
        deviceSession.setExpiresIn(expiresIn);

        var accessToken = jwtService.generateAccessToken(user, newAccessTokenId, deviceId);
        var refreshToken = jwtService.generateRefreshToken(user, newRefreshTokenId, deviceId, expiresIn);

        cookieService.addRefreshTokenCookie(refreshToken, refreshTokenExpiration);

        return AuthResponse.builder()
                .requires2FA(false)
                .accessToken(accessToken)
                .tokenType("Bearer")
                .expiresIn(expiresIn)
                .build();
    }

    @Transactional
    @Override
    public void revokeTokens(DeviceSession deviceSession) {
        authTokenBlacklistService.addToBlackList(
                deviceSession.getAccessTokenId(),
                deviceSession.getLastRefreshedAt().plus(accessTokenExpiration),
                "Access token revoked"
        );
        authTokenBlacklistService.addToBlackList(
                deviceSession.getRefreshTokenId(),
                deviceSession.getLastRefreshedAt().plus(refreshTokenExpiration),
                "Refresh token revoked"
        );
    }


}
