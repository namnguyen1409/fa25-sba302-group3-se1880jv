package sba.group3.backendmvc.service.auth;

import com.yubico.webauthn.data.PublicKeyCredentialCreationOptions;
import com.yubico.webauthn.data.PublicKeyCredentialRequestOptions;

import java.util.UUID;

public interface PasskeyService {

    PublicKeyCredentialCreationOptions startRegistration(UUID userId, String username, String displayName);

    void finishRegistration(UUID userId, String credential);

    PublicKeyCredentialRequestOptions startLogin(String username);
}
