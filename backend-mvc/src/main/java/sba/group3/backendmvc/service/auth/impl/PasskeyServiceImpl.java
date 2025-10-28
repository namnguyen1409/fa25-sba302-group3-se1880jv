package sba.group3.backendmvc.service.auth.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.yubico.webauthn.*;
import com.yubico.webauthn.data.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.request.auth.passkey.FinishLoginRequest;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.dto.response.auth.passkey.StartPasskeyLoginResponse;
import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.enums.CacheKey;
import sba.group3.backendmvc.enums.LoginStatus;
import sba.group3.backendmvc.enums.MfaType;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.repository.auth.MfaConfigRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.LoginAttemptService;
import sba.group3.backendmvc.service.auth.PasskeyService;
import sba.group3.backendmvc.service.auth.TokenIssuerService;
import sba.group3.backendmvc.service.infrastructure.CacheService;
import sba.group3.backendmvc.service.user.DeviceSessionService;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.time.Duration;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PasskeyServiceImpl implements PasskeyService {
    RelyingParty relyingParty;
    MfaConfigRepository mfaConfigRepository;
    CacheService cacheService;
    UserRepository userRepository;
    TokenIssuerService tokenIssuerService;
    DeviceSessionService deviceSessionService;
    private final HttpServletRequest httpServletRequest;
    private final LoginAttemptService loginAttemptService;

    @NonFinal
    @Value("${security.jwt.refresh-token.expiration}")
    Duration refreshTokenExpiration;

    @NonFinal
    @Value("${security.webauthn.hostname}")
    String hostname;

    @NotNull
    private static String headerOrEmpty(@NotNull HttpServletRequest req, String name) {
        return AuthServiceImpl.headerOrEmpty(req, name);
    }

    private static String clientIp(@NotNull HttpServletRequest req) {
        return AuthServiceImpl.clientIp(req);
    }

    private ByteArray uuidToByteArray(UUID uuid) {
        ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
        bb.putLong(uuid.getMostSignificantBits());
        bb.putLong(uuid.getLeastSignificantBits());
        return new ByteArray(bb.array());
    }

    @Override
    public PublicKeyCredentialCreationOptions startRegistration(UUID userId, String username, String displayName) {
        byte[] userHandle = new byte[32];
        new SecureRandom().nextBytes(userHandle);
        var user = UserIdentity.builder()
                .name(username)
                .displayName(displayName)
                .id(uuidToByteArray(userId))
                .build();
        var options = relyingParty.startRegistration(
                StartRegistrationOptions
                        .builder()
                        .user(user)
                        .timeout(Optional.of(6000L))
                        .build());
        try {
            cacheService.put(
                    CacheKey.PASSKEY_REGISTRATION_OPTIONS.of(userId.toString()),
                    options.toJson(),
                    Duration.ofMinutes(10)
            );
        } catch (JsonProcessingException e) {
            log.error("Failed to cache registration options for user {}", userId, e);
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }
        return options;
    }

    @Override
    public void finishRegistration(UUID userId, String credential) {
        try {
            var json = cacheService.get(
                    CacheKey.PASSKEY_REGISTRATION_OPTIONS.of(userId.toString()),
                    String.class
            );
            if (json == null) {
                throw new IllegalStateException("No registration options found for user " + userId);
            }
            var requestOptions = PublicKeyCredentialCreationOptions.fromJson(json);
            PublicKeyCredential<AuthenticatorAttestationResponse, ClientRegistrationExtensionOutputs> pkc =
                    PublicKeyCredential.parseRegistrationResponseJson(credential);
            FinishRegistrationOptions options = FinishRegistrationOptions.builder()
                    .request(requestOptions)
                    .response(pkc)
                    .build();
            RegistrationResult result = relyingParty.finishRegistration(options);
            var cfg = MfaConfig.builder()
                    .user(userRepository.findById(userId).orElseThrow())
                    .mfaType(MfaType.PASSKEY)
                    .credentialId(result.getKeyId().getId().getBase64Url())
                    .publicKey(result.getPublicKeyCose().getBytes())
                    .signCount(result.getSignatureCount())
                    .primary(true)
                    .build();

            mfaConfigRepository.save(cfg);

        } catch (Exception e) {
            e.printStackTrace();
            log.info("Registration failed for user " + userId, e);
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }

    }


    @Override
    public StartPasskeyLoginResponse startLogin() {
        var start = relyingParty.startAssertion(StartAssertionOptions.builder()
                .timeout(Optional.of(6000L))
                .userVerification(UserVerificationRequirement.PREFERRED)
                .build()
        );
        String requestId = UUID.randomUUID().toString();
        try {
            cacheService.put(
                    CacheKey.PASSKEY_AUTHENTICATION_OPTIONS.of(requestId),
                    start.toJson(),
                    Duration.ofMinutes(10)
            );
        } catch (JsonProcessingException e) {
            log.error("Failed to cache authentication options for request {}", requestId, e);
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }
        return new StartPasskeyLoginResponse(requestId, start.getPublicKeyCredentialRequestOptions());
    }

    @Transactional
    @Override
    public AuthResponse finishLogin(FinishLoginRequest request) {
        var ip = clientIp(httpServletRequest);
        var userAgent = headerOrEmpty(httpServletRequest, "user-agent");
        try {
            var json = cacheService.get(
                    CacheKey.PASSKEY_AUTHENTICATION_OPTIONS.of(request.requestId()),
                    String.class
            );
            if (json == null) {
                throw new IllegalStateException("No authentication options found for request " + request.requestId());
            }
            var requestOptions = AssertionRequest.fromJson(json);
            var response = PublicKeyCredential.parseAssertionResponseJson(request.responseJson());
            var result = relyingParty.finishAssertion(
                    FinishAssertionOptions.builder()
                            .request(requestOptions)
                            .response(response)
                            .build()
            );

            if (!result.isSuccess()) {
                throw new AppException(ErrorCode.PASSKEY_AUTHENTICATION_FAILED);
            }
//            UUID userId = UUID.fromString(
//                    new String(result.getCredential().getCredentialId().getBytes(), StandardCharsets.UTF_8)
//            );
//            var user = userRepository.findById(userId)
//                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            var credId = result.getCredential().getCredentialId().getBase64Url();
            var mfaConfig = mfaConfigRepository.findByCredentialId(credId)
                    .orElseThrow(() -> new AppException(ErrorCode.PASSKEY_AUTHENTICATION_FAILED));
            var user = mfaConfig.getUser();
            mfaConfigRepository.updateSignCount(user.getId(), MfaType.PASSKEY, credId, result.getSignatureCount());

            var session = deviceSessionService.ensureDeviceSession(
                    user,
                    request.deviceId(),
                    request.rememberMe(),
                    true
            );
            session.setIpAddress(ip);
            session.setUserAgent(userAgent);
            loginAttemptService.recordAttempt(user, ip, userAgent, LoginStatus.SUCCESS, "PASSKEY");
            return tokenIssuerService.issue(user, session, request.deviceId());

        } catch (Exception e) {
            log.info("Authentication failed for request {}", request.requestId(), e);
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }
    }


}
