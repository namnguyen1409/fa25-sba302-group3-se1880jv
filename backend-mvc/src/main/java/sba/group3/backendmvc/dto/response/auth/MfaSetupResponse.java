package sba.group3.backendmvc.dto.response.auth;


public record MfaSetupResponse(
        String secret,
        String qrUri
) {
}
