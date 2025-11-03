package sba.group3.backendmvc.service.auth.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import sba.group3.backendmvc.controller.auth.AuthController;
import sba.group3.backendmvc.dto.request.auth.LoginRequest;
import sba.group3.backendmvc.dto.request.auth.OAuthLoginRequest;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.dto.response.user.MeResponse;
import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.entity.auth.OAuthAccount;
import sba.group3.backendmvc.entity.patient.Patient;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.entity.user.UserProfile;
import sba.group3.backendmvc.enums.CacheKey;
import sba.group3.backendmvc.enums.LoginStatus;
import sba.group3.backendmvc.enums.OAuthProvider;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.user.DeviceSessionMapper;
import sba.group3.backendmvc.mapper.user.UserMapper;
import sba.group3.backendmvc.repository.auth.MfaConfigRepository;
import sba.group3.backendmvc.repository.auth.OAuthAccountRepository;
import sba.group3.backendmvc.repository.auth.OtpChallengeRepository;
import sba.group3.backendmvc.repository.patient.PatientRepository;
import sba.group3.backendmvc.repository.user.DeviceSessionRepository;
import sba.group3.backendmvc.repository.user.UserProfileRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.*;
import sba.group3.backendmvc.service.infrastructure.CacheService;
import sba.group3.backendmvc.service.infrastructure.CookieService;
import sba.group3.backendmvc.service.infrastructure.EmailSender;
import sba.group3.backendmvc.service.user.DeviceSessionService;
import sba.group3.backendmvc.service.user.RoleService;

import java.time.Duration;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    DeviceSessionMapper deviceSessionMapper;
    UserMapper userMapper;

    MfaConfigService mfaConfigService;
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    LoginAttemptService loginAttemptService;
    HttpServletRequest httpServletRequest;
    DeviceSessionRepository deviceSessionRepository;
    OtpChallengeService otpChallengeService;
    TokenIssuerService tokenIssuerService;

    DeviceSessionService deviceSessionService;
    ObjectMapper objectMapper;
    OAuthAccountRepository oAuthAccountRepository;
    CookieService cookieService;
    JwtDecoder jwtDecoder;
    RoleService roleService;
    UserProfileRepository userProfileRepository;
    CacheService cacheService;
    EmailSender emailSender;
    OtpChallengeRepository otpChallengeRepository;
    MfaConfigRepository mfaConfigRepository;
    PatientRepository patientRepository;

    @NonFinal
    @Value("${spring.security.oauth2.client.registration.github.client-id}")
    String githubClientId;

    @NonFinal
    @Value("${spring.security.oauth2.client.registration.github.client-secret}")
    String githubClientSecret;

    @NonFinal
    @Value("${app.frontend.url}")
    String frontendUrl;

    @NotNull
    public static String headerOrEmpty(@NotNull HttpServletRequest req, String name) {
        String v = req.getHeader(name);
        return v == null ? "" : v;
    }

    public static String clientIp(@NotNull HttpServletRequest req) {
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
        String userAgent = headerOrEmpty(httpServletRequest, "user-agent");
        var user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new AppException(ErrorCode.AUTH_INVALID_CREDENTIALS));
        LoginStatus status = null;
        try {
            if (user.isLocked()) {
                status = LoginStatus.LOCKED;
                throw new AppException(ErrorCode.ACCOUNT_LOCKED);
            }
            log.info("Falure count: {}",
                    loginAttemptService.countFailures(user, Duration.ofMinutes(15))
            );
            if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
                if (loginAttemptService.countFailures(user, Duration.ofMinutes(15)) >= 5) {
                    user.setLocked(true);
                    userRepository.save(user);
                    status = LoginStatus.LOCKED;
                    throw new AppException(ErrorCode.ACCOUNT_LOCKED);
                }
                status = LoginStatus.FAILURE;
                throw new AppException(ErrorCode.AUTH_INVALID_CREDENTIALS);
            }

            var existingSession = deviceSessionRepository.findByUserIdAndDeviceId(user.getId(), loginRequest.getDeviceId())
                    .orElse(null);
            var trusted = existingSession != null && existingSession.isTrusted();
            if (user.isMfaEnabled() && !trusted) {
                var configs = mfaConfigService.findAllByUserId(user.getId());
                if (!configs.isEmpty()) {
                    var primary = configs
                            .stream()
                            .filter(MfaConfig::getPrimary).findFirst()
                            .orElseGet(configs::getFirst);
                    var challenge = otpChallengeService.create(user, primary);
                    loginAttemptService.recordAttempt(user, ip, userAgent, LoginStatus.MFA_PENDING, "PASSWORD");
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
            var deviceSession = deviceSessionService.ensureDeviceSession(
                    user,
                    loginRequest.getDeviceId(),
                    loginRequest.getRememberMe(),
                    true
            );
            deviceSession.setIpAddress(ip);
            deviceSession.setUserAgent(userAgent);
            status = LoginStatus.SUCCESS;
            return tokenIssuerService.issue(user, deviceSession, loginRequest.getDeviceId());
        } finally {
            if (status != null) {
                loginAttemptService.recordAttempt(user, ip, userAgent, status, "PASSWORD");
            }
        }
    }

    @Transactional
    @Override
    public AuthResponse loginWithOAuth(OAuthLoginRequest oAuthLoginRequest) {
        String ip = clientIp(httpServletRequest);
        String userAgent = headerOrEmpty(httpServletRequest, "user-agent");
        var userInfo = verifyToken(oAuthLoginRequest.getProvider(), oAuthLoginRequest.getAccessToken());
        String email = userInfo.get("email").asText();
        String name = userInfo.has("name") ? userInfo.get("name").asText() : "Unknown";
        String providerUserId = userInfo.has("sub") ? userInfo.get("sub").asText() : userInfo.get("id").asText();
        String avatar = userInfo.has("picture") ? userInfo.get("picture").asText() : null;

        Optional<OAuthAccount> existingOAuth = oAuthAccountRepository.findByProviderAndProviderUserId(
                oAuthLoginRequest.getProvider(), providerUserId);

        User user;
        if (existingOAuth.isPresent()) {
            user = existingOAuth.get().getUser();
            if (user.isLocked()) {
                throw new AppException(ErrorCode.ACCOUNT_LOCKED);
            }
        } else {
            user = userRepository.findByEmail(email).orElseGet(() -> {
                User newUser = User.builder()
                        .username(email)
                        .email(email)
                        .password(passwordEncoder.encode(UUID.randomUUID().toString()))
                        .active(true)
                        .firstLogin(true)
                        .roles(new HashSet<>(roleService.findDefaultRoles()))
                        .build();
                return userRepository.save(newUser);
            });

            // create OAuth account if missing
            if (!oAuthAccountRepository.existsByProviderAndProviderUserId(
                    oAuthLoginRequest.getProvider(), providerUserId)) {
                OAuthAccount oauthAccount = OAuthAccount.builder()
                        .user(user)
                        .provider(oAuthLoginRequest.getProvider())
                        .providerUserId(providerUserId)
                        .email(email)
                        .name(name)
                        .avatarUrl(avatar)
                        .build();
                oAuthAccountRepository.save(oauthAccount);
            }

            // find or update profile
            var profile = userProfileRepository.findByUser_Id(user.getId())
                    .orElseGet(() -> UserProfile.builder()
                            .user(user)
                            .build());

            if (name != null) profile.setFullName(name);
            if (avatar != null) profile.setAvatarUrl(avatar);

            userProfileRepository.save(profile);

            // create patient
            var patent = Patient.builder()
                    .user(user)
                    .patientCode(generateCode())
                    .fullName(name)
                    .email(email)
                    .build();
            patientRepository.save(patent);
        }

        var deviceSession = deviceSessionService.ensureDeviceSession(
                user,
                oAuthLoginRequest.getDeviceId(),
                oAuthLoginRequest.getRememberMe(),
                true
        );
        deviceSession.setIpAddress(ip);
        deviceSession.setUserAgent(userAgent);
        loginAttemptService.recordAttempt(user, ip, userAgent, LoginStatus.SUCCESS, "OAUTH_" + oAuthLoginRequest.getProvider().name());
        return tokenIssuerService.issue(user, deviceSession, oAuthLoginRequest.getDeviceId());
    }

    public String generateCode() {
        long count = patientRepository.count();
        return "PT-" + String.format("%06d", count + 1);
    }


    @Transactional
    @Override
    public AuthResponse refreshToken() {
        var headerDeviceId = headerOrEmpty(httpServletRequest, "X-Device-ID");
        String refreshToken = cookieService.getRefreshToken()
                .orElseThrow(() -> new AppException(ErrorCode.REFRESH_TOKEN_MISSING));
        try {
            var jwt = jwtDecoder.decode(refreshToken);

            UUID userId = UUID.fromString(jwt.getSubject());
            String deviceId = jwt.getClaimAsString("deviceId");
            if (headerDeviceId.isEmpty() || !headerDeviceId.equals(deviceId)) {
                throw new AppException(ErrorCode.UNAUTHORIZED);
            }
            var user = userRepository.findById(userId)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

            var deviceSession = deviceSessionRepository.findByUserIdAndDeviceId(userId, deviceId)
                    .orElseThrow(() -> new AppException(ErrorCode.DEVICE_SESSION_NOT_FOUND));
            if (deviceSession.isRevoked()) {
                throw new AppException(ErrorCode.REFRESH_TOKEN_INVALID);
            }
            if (!deviceSession.getRefreshTokenId().equals(jwt.getId())) {
                throw new AppException(ErrorCode.REFRESH_TOKEN_INVALID);
            }
            return tokenIssuerService.refresh(user, deviceSession, deviceId);
        } catch (Exception e) {
            throw new AppException(ErrorCode.REFRESH_TOKEN_INVALID);
        }
    }


    @Transactional(readOnly = true)
    @Override
    public MeResponse getCurrentUser(UUID userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        var headerDeviceId = headerOrEmpty(httpServletRequest, "X-Device-ID");
        var dto = userMapper.toDto(user);
        deviceSessionRepository.findByUserIdAndDeviceId(userId, headerDeviceId)
                .ifPresent(
                        deviceSession -> dto.setDevice(deviceSessionMapper.toDto(deviceSession))
                );
        return dto;
    }

    @Override
    public void logout(UUID userId) {
        var headerDeviceId = headerOrEmpty(httpServletRequest, "X-Device-ID");
        var deviceSession = deviceSessionRepository.findByUserIdAndDeviceId(userId, headerDeviceId)
                .orElseThrow(() -> new AppException(ErrorCode.DEVICE_SESSION_NOT_FOUND));
        tokenIssuerService.revokeTokens(deviceSession);
        deviceSession.setRevoked(true);
        deviceSession.setTrusted(false);
        deviceSessionRepository.save(deviceSession);
        cookieService.clearRefreshTokenCookie();
    }

    @Override
    public void requestPasswordReset(AuthController.PasswordResetRequest request) {
        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        String token = UUID.randomUUID().toString();

        // TTL 15 phút
        cacheService.put(CacheKey.RESET_PASSWORD_TOKEN.of(token), user.getId().toString(), Duration.ofMinutes(15));

        String resetLink = frontendUrl + "/reset-password?token=" + token;
        emailSender.send(
                user.getEmail(),
                "Đặt lại mật khẩu",
                """
                        <p>Xin chào %s,</p>
                        <p>Nhấn vào liên kết để đặt lại mật khẩu:</p>
                        <p><a href="%s">%s</a></p>
                        <p>Liên kết có hiệu lực trong 15 phút.</p>
                        """.formatted(user.getUsername(), resetLink, resetLink)
        );

    }

    @Transactional
    @Override
    public void confirmPasswordReset(AuthController.PasswordResetConfirmRequest request) {
        String userId = cacheService.get(CacheKey.RESET_PASSWORD_TOKEN.of(request.token()), String.class);
        if (userId == null) {
            throw new AppException(ErrorCode.RESET_PASSWORD_TOKEN_INVALID_OR_EXPIRED);
        }

        var user = userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);
    }

    @Transactional
    @Override
    public AuthResponse verifyMfa(AuthController.MfaVerifyRequest request) {
        var challenge = otpChallengeRepository.findById(request.challengeId())
                .orElseThrow(() -> new AppException(ErrorCode.OTP_CHALLENGE_NOT_FOUND));

        var config = mfaConfigRepository.findByUserIdAndMfaTypeAndRevokedFalse(challenge.getUser().getId(), challenge.getMfaType())
                .orElseThrow(() -> new AppException(ErrorCode.MFA_TYPE_NOT_FOUND));

        otpChallengeService.verify(challenge.getUser(), config, challenge.getId(), request.code());

        // Sau khi verify thành công
        var deviceSession = deviceSessionService.ensureDeviceSession(
                challenge.getUser(),
                request.deviceId(),
                Boolean.TRUE.equals(request.rememberMe()),
                true
        );
        deviceSession.setTrusted(true);

        return tokenIssuerService.issue(challenge.getUser(), deviceSession, request.deviceId());
    }

    @Transactional
    @Override
    public AuthResponse switchMfa(AuthController.SwitchMfaRequest request) {
        var challenge = otpChallengeRepository.findById(request.challengeId())
                .orElseThrow(() -> new AppException(ErrorCode.OTP_CHALLENGE_NOT_FOUND));

        var user = challenge.getUser();

        // find new config
        var config = mfaConfigRepository.findByUserIdAndMfaTypeAndRevokedFalse(user.getId(), request.mfaType())
                .orElseThrow(() -> new AppException(ErrorCode.MFA_TYPE_NOT_FOUND));

        // create new challenge
        var newChallenge = otpChallengeService.create(user, config);

        return AuthResponse.builder()
                .requires2FA(true)
                .mfaTypes(
                        mfaConfigService.findAllByUserId(user.getId())
                                .stream().map(MfaConfig::getMfaType).toList()
                )
                .defaultMfaType(config.getMfaType())
                .challengeId(newChallenge.getId())
                .build();
    }


    private JsonNode verifyToken(OAuthProvider provider, String accessToken) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        try {
            switch (provider) {
                case GOOGLE -> {
                    String url = "https://www.googleapis.com/oauth2/v3/tokeninfo?access_token=" + accessToken;
                    String response = restTemplate.getForObject(url, String.class);
                    return objectMapper.readTree(response);
                }

                case FACEBOOK -> {
                    String url = "https://graph.facebook.com/me?fields=id,name,email,picture&access_token=" + accessToken;
                    String response = restTemplate.getForObject(url, String.class);
                    return objectMapper.readTree(response);
                }

                case GITHUB -> {
                    String realAccessToken = accessToken.length() < 50
                            ? exchangeCodeForGitHubToken(accessToken)
                            : accessToken;

                    HttpHeaders userHeaders = new HttpHeaders();
                    userHeaders.setBearerAuth(realAccessToken);
                    HttpEntity<Void> userEntity = new HttpEntity<>(userHeaders);

                    ResponseEntity<String> userResponse = restTemplate.exchange(
                            "https://api.github.com/user",
                            HttpMethod.GET,
                            userEntity,
                            String.class
                    );

                    JsonNode userInfo = objectMapper.readTree(userResponse.getBody());

                    if (!userInfo.has("email") || userInfo.get("email").isNull()) {
                        ResponseEntity<String> emailResponse = restTemplate.exchange(
                                "https://api.github.com/user/emails",
                                HttpMethod.GET,
                                userEntity,
                                String.class
                        );
                        JsonNode emails = objectMapper.readTree(emailResponse.getBody());
                        if (emails.isArray()) {
                            for (JsonNode emailNode : emails) {
                                if (emailNode.has("primary") && emailNode.get("primary").asBoolean()) {
                                    ((ObjectNode) userInfo).put("email", emailNode.get("email").asText());
                                    break;
                                }
                            }
                        }
                    }
                    return userInfo;
                }

                default -> throw new AppException(ErrorCode.UNSUPPORTED_OAUTH_PROVIDER);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to verify " + provider + " token: " + e.getMessage(), e);
        }
    }


    private String exchangeCodeForGitHubToken(String code) throws JsonProcessingException {
        String tokenUrl = "https://github.com/login/oauth/access_token";

        HttpHeaders headers = new HttpHeaders();
        headers.set("Accept", "application/json");

        Map<String, String> body = Map.of(
                "client_id", githubClientId,
                "client_secret", githubClientSecret,
                "code", code,
                "redirect_uri", "http://localhost:5173/oauth/callback/github"
        );

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Map<String, String>> entity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(tokenUrl, entity, String.class);

        JsonNode node = objectMapper.readTree(response.getBody());
        return node.get("access_token").asText();
    }


}
