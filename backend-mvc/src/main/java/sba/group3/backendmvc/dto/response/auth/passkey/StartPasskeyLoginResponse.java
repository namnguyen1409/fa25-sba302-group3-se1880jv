package sba.group3.backendmvc.dto.response.auth.passkey;


import com.yubico.webauthn.data.PublicKeyCredentialRequestOptions;

public record StartPasskeyLoginResponse(
        String requestId,
        PublicKeyCredentialRequestOptions requestOptions
) {
}
