package sba.group3.backendmvc.dto.request.auth.passkey;

public record FinishLoginRequest(
        String requestId,
        String deviceId,
        boolean rememberMe,
        String responseJson
) {}
