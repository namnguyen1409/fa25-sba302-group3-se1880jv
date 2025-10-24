package sba.group3.backendmvc.service.auth.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.request.auth.LoginRequest;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.entity.user.DeviceSession;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.LoginStatus;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.repository.user.DeviceSessionRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.*;
import sba.group3.backendmvc.service.infrastructure.CookieService;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {

    private final MfaConfigService mfaConfigService;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    LoginAttemptService loginAttemptService;
    HttpServletRequest httpServletRequest;
    DeviceSessionRepository deviceSessionRepository;
    OtpChallengeService otpChallengeService;
    JwtService jwtService;
    CookieService cookieService;
    @NonFinal
    @Value("${security.jwt.refresh-token.expiration}")
    Duration refreshTokenExpiration;

    @NotNull
    private static String headerOrEmpty(@NotNull HttpServletRequest req, String name) {
        String v = req.getHeader(name);
        return v == null ? "" : v;
    }

    private static String clientIp(@NotNull HttpServletRequest req) {
        String xff = req.getHeader("X-Forwarded-For");
        if (xff != null && !xff.isBlank()) {
            int comma = xff.indexOf(',');
            return comma > 0 ? xff.substring(0, comma).trim() : xff.trim();
        }
        return req.getRemoteAddr();
    }

    @Transactional(noRollbackFor = AppException.class)
    @Override
    public AuthResponse login(LoginRequest loginRequest) {
        String ip = clientIp(httpServletRequest);
        String userAgent = headerOrEmpty(httpServletRequest, "User-Agent");
        var user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.AUTH_INVALID_CREDENTIALS));
        LoginStatus status = null;
        try {
            if (user.isLocked()) {
                status = LoginStatus.LOCKED;
                throw new AppException(ErrorCode.ACCOUNT_LOCKED);
            }
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                if (loginAttemptService.countFailures(user, Duration.ofMinutes(15)) >=5) {
                    user.setLocked(true);
                    userRepository.save(user);
                    status = LoginStatus.LOCKED;
                    throw new AppException(ErrorCode.ACCOUNT_LOCKED);
                }
                status = LoginStatus.FAILURE;
                throw new AppException(ErrorCode.AUTH_INVALID_CREDENTIALS);
            }

            var deviceSession = deviceSessionRepository.findByUserIdAndDeviceId(user.getId(), loginRequest.getDeviceId())
                    .orElseGet(() -> DeviceSession.builder()
                            .user(user)
                            .deviceId(loginRequest.getDeviceId())
                            .ipAddress(ip)
                            .userAgent(userAgent)
                            .deviceName(loginRequest.getDeviceName())
                            .rememberMe(Boolean.TRUE.equals(loginRequest.getRememberMe()))
                            .build()
                    );
            if (user.isMfaEnabled() && !deviceSession.isTrusted()) {
                var configs = mfaConfigService.findAllByUserId(user.getId());
                if (!configs.isEmpty()) {
                    var primary = configs
                            .stream()
                            .filter(MfaConfig::getPrimary).findFirst()
                            .orElseGet(configs::getFirst);
                    var challenge = otpChallengeService.create(user, primary);
                    loginAttemptService.recordAttempt(user, ip, userAgent, LoginStatus.MFA_PENDING);
                    return AuthResponse.builder()
                            .requires2FA(true)
                            .mfaTypes(configs.stream().map(MfaConfig::getMfaType).toList())
                            .defaultMfaType(primary.getMfaType())
                            .challengeId(challenge.getId())
                            .build();
                } else {
                    log.warn("User {} has MFA enabled but no MFA configs found. Bypassing MFA.", user.getUsername());
                }
            }
            status = LoginStatus.SUCCESS;
            return issueTokens(user, deviceSession, loginRequest.getDeviceId());
        } finally {
            if (status != null) {
                loginAttemptService.recordAttempt(user, ip, userAgent, status);
            }
        }
    }

    private AuthResponse issueTokens(User user, DeviceSession deviceSession, String deviceId) {
        var accessTokenId = UUID.randomUUID().toString();
        var refreshTokenId = UUID.randomUUID().toString();
        deviceSession.setAccessTokenId(accessTokenId);
        deviceSession.setRefreshTokenId(refreshTokenId);
        deviceSessionRepository.save(deviceSession);

        var expiresIn = deviceSession.isRememberMe()
                ? Instant.now().plus(refreshTokenExpiration)
                : Instant.now().plus(1, ChronoUnit.DAYS);

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




}
