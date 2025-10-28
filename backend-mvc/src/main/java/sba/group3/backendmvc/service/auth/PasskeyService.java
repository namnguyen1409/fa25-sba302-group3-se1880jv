package sba.group3.backendmvc.service.auth;

import com.yubico.webauthn.data.PublicKeyCredentialCreationOptions;
import sba.group3.backendmvc.dto.request.auth.passkey.FinishLoginRequest;
import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.dto.response.auth.passkey.StartPasskeyLoginResponse;

import java.util.UUID;

public interface PasskeyService {

    PublicKeyCredentialCreationOptions startRegistration(UUID userId, String username, String displayName);

    void finishRegistration(UUID userId, String credential);

    StartPasskeyLoginResponse startLogin();

    AuthResponse finishLogin(FinishLoginRequest request);
}
