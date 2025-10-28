export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse {
    accessToken: string;
    refreshToken: string;
    expiresIn: number;
}

export interface RegisterRequest {
    username: string;
    email: string;
    password: string;
}

/**
 * public record FinishLoginRequest(
        String requestId,
        String deviceId,
        boolean rememberMe,
        String responseJson
) {}

 */

export interface FinishLoginPasskeyRequest {
    requestId: string;
    deviceId: string;
    rememberMe: boolean;
    responseJson: string;
}

export interface StartPasskeyLoginResponse {
    requestId: string;
    requestOptions: any;
}