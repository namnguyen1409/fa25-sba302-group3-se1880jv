package sba.group3.backendmvc.service.auth.impl;

import com.yubico.webauthn.FinishRegistrationOptions;
import com.yubico.webauthn.RegistrationResult;
import com.yubico.webauthn.RelyingParty;
import com.yubico.webauthn.StartRegistrationOptions;
import com.yubico.webauthn.data.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.enums.CacheKey;
import sba.group3.backendmvc.enums.MfaType;
import sba.group3.backendmvc.repository.auth.MfaConfigRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.PasskeyService;
import sba.group3.backendmvc.service.infrastructure.CacheService;

import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PasskeyServiceImpl implements PasskeyService {
    RelyingParty relyingParty;
    MfaConfigRepository mfaConfigRepository;
    CacheService cacheService;
    private final UserRepository userRepository;

    @Override
    public PublicKeyCredentialCreationOptions startRegistration(UUID userId) {
        var user = UserIdentity.builder()
                .name(userId.toString())
                .displayName("User " + userId)
                .id(new ByteArray(userId.toString().getBytes(StandardCharsets.UTF_8)))
                .build();
        var options = relyingParty.startRegistration(
                StartRegistrationOptions
                        .builder()
                        .user(user)
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
        try{
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
            throw new RuntimeException(e);
        }

    }


//    public PublicKeyCredentialRequestOptions startLogin()


}
