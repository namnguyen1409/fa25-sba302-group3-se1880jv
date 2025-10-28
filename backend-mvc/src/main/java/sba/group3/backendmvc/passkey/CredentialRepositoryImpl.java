package sba.group3.backendmvc.passkey;

import com.yubico.webauthn.CredentialRepository;
import com.yubico.webauthn.RegisteredCredential;
import com.yubico.webauthn.data.ByteArray;
import com.yubico.webauthn.data.PublicKeyCredentialDescriptor;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.enums.MfaType;
import sba.group3.backendmvc.repository.auth.MfaConfigRepository;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CredentialRepositoryImpl implements CredentialRepository {

    private final MfaConfigRepository mfaConfigRepository;

    @Override
    public Set<PublicKeyCredentialDescriptor> getCredentialIdsForUsername(String username) {
        var userConfigs = mfaConfigRepository.findAllByUserIdAndMfaType(
                UUID.fromString(username),
                MfaType.PASSKEY
        );
        return userConfigs.stream()
                .map(config -> PublicKeyCredentialDescriptor.builder()
                        .id(new ByteArray(Base64.getUrlDecoder().decode(config.getCredentialId())))
                        .build()
                ).collect(Collectors.toSet());
    }

    @Override
    public Optional<ByteArray> getUserHandleForUsername(String username) {
        return Optional.of(new ByteArray(username.getBytes(StandardCharsets.UTF_8)));
    }

    @Override
    public Optional<String> getUsernameForUserHandle(ByteArray userHandle) {
        return Optional.of(new String(userHandle.getBytes(), StandardCharsets.UTF_8));
    }

    @Override
    public Optional<RegisteredCredential> lookup(ByteArray credentialId, ByteArray userHandle) {
        return mfaConfigRepository.findByCredentialId(credentialId.getBase64Url())
                .map(config -> RegisteredCredential.builder()
                        .credentialId(credentialId)
                        .userHandle(userHandle)
                        .publicKeyCose(new ByteArray(config.getPublicKey()))
                        .signatureCount(config.getSignCount())
                        .build()
                );
    }

    @Override
    public Set<RegisteredCredential> lookupAll(ByteArray credentialId) {
        // Tìm trong DB credential có id = credentialId
        String credIdB64 = credentialId.getBase64Url();
        return mfaConfigRepository.findByCredentialId(credIdB64)
                .map(config -> {
                    UUID userId = config.getUser().getId();
                    // convert UUID → 16-byte userHandle
                    ByteBuffer bb = ByteBuffer.wrap(new byte[16]);
                    bb.putLong(userId.getMostSignificantBits());
                    bb.putLong(userId.getLeastSignificantBits());
                    ByteArray userHandle = new ByteArray(bb.array());

                    return Set.of(
                            RegisteredCredential.builder()
                                    .credentialId(credentialId)
                                    .userHandle(userHandle)
                                    .publicKeyCose(new ByteArray(config.getPublicKey()))
                                    .signatureCount(config.getSignCount())
                                    .build()
                    );
                })
                .orElse(Collections.emptySet());
    }

}
