package sba.group3.backendmvc.service.auth.impl;

import com.yubico.webauthn.*;
import com.yubico.webauthn.data.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.entity.user.DeviceSession;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.CacheKey;
import sba.group3.backendmvc.enums.MfaType;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.repository.auth.MfaConfigRepository;
import sba.group3.backendmvc.repository.user.DeviceSessionRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.JwtService;
import sba.group3.backendmvc.service.auth.PasskeyService;
import sba.group3.backendmvc.service.infrastructure.CacheService;
import sba.group3.backendmvc.service.infrastructure.CookieService;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PasskeyServiceImpl implements PasskeyService {
    RelyingParty relyingParty;
    MfaConfigRepository mfaConfigRepository;
    CacheService cacheService;
    UserRepository userRepository;
    DeviceSessionRepository deviceSessionRepository;
    JwtService jwtService;
    CookieService cookieService;

    @NonFinal
    @Value("${security.jwt.refresh-token.expiration}")
    Duration refreshTokenExpiration;

    @Override
    public PublicKeyCredentialCreationOptions startRegistration(UUID userId, String username, String displayName) {
        var user = UserIdentity.builder()
                .name(username)
                .displayName(displayName)
                .id(new ByteArray(userId.toString().getBytes(StandardCharsets.UTF_8)))
                .build();
        var options = relyingParty.startRegistration(
                StartRegistrationOptions
                        .builder()
                        .user(user)
                        .timeout(Optional.of(6000L))
                        .build());
        cacheService.put(
                CacheKey.PASSKEY_REGISTRATION_OPTIONS.of(userId.toString()),
                options,
                Duration.ofMinutes(10)
        );
        return options;
    }

    @Override
    public void finishRegistration(UUID userId, String credential) {
        try {
            var requestOptions = cacheService.get(
                    CacheKey.PASSKEY_REGISTRATION_OPTIONS.of(userId.toString()),
                    PublicKeyCredentialCreationOptions.class
            );
            if (requestOptions == null) {
                throw new IllegalStateException("No registration options found for user " + userId);
            }
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
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }

    }


    @Override
    public PublicKeyCredentialRequestOptions startLogin(String username) {
        var start = relyingParty.startAssertion(StartAssertionOptions.builder()
                .username(username)
                .timeout(Optional.of(6000L))
                .build()
        );
        cacheService.put(
                CacheKey.PASSKEY_AUTHENTICATION_OPTIONS.of(username),
                start,
                Duration.ofMinutes(10)
        );
        return start.getPublicKeyCredentialRequestOptions();
    }

    public AuthResponse finishLogin(String username, String deviceId, boolean rememberMe, String credential) {
        try {
            var requestOptions = cacheService.get(
                    CacheKey.PASSKEY_AUTHENTICATION_OPTIONS.of(username),
                    AssertionRequest.class
            );
            if (requestOptions == null) {
                throw new IllegalStateException("No authentication options found for user " + username);
            }
            var response = PublicKeyCredential.parseAssertionResponseJson(credential);
            var result = relyingParty.finishAssertion(
                    FinishAssertionOptions.builder()
                            .request(requestOptions)
                            .response(response)
                            .build()
            );

            if (!result.isSuccess()) {
                throw new AppException(ErrorCode.PASSKEY_AUTHENTICATION_FAILED);
            }
            var user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
            var credId = result.getCredential().getCredentialId().getBase64Url();
            mfaConfigRepository.updateSignCount(user.getId(), MfaType.PASSKEY, credId, result.getSignatureCount());

            var session = deviceSessionRepository.findByUserIdAndDeviceId(user.getId(), deviceId)
                    .orElseGet(() -> DeviceSession.builder()
                            .user(user)
                            .deviceId(deviceId)
                            .trusted(true)
                            .rememberMe(rememberMe)
                            .build());
            session.setTrusted(true);
            deviceSessionRepository.save(session);

            return issueTokens(user, session, deviceId);

        } catch (Exception e) {
            throw new AppException(ErrorCode.UNCATEGORIZED);
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
