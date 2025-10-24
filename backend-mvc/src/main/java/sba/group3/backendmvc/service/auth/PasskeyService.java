package sba.group3.backendmvc.service.auth;

import com.yubico.webauthn.data.PublicKeyCredentialCreationOptions;

import java.util.UUID;

public interface PasskeyService {
    PublicKeyCredentialCreationOptions startRegistration(UUID userId);

    void finishRegistration(UUID userId, String credential);
}
