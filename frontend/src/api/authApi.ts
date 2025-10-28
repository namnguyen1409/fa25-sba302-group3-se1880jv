import type { FinishLoginPasskeyRequest, StartPasskeyLoginResponse } from "@/types/auth.dto";
import { apiClient } from "./client";
import type { MeResponse } from "@/types/auth";

export interface LoginRequest {
    username: string;
    password: string;
    deviceId: string;
    rememberMe?: boolean;
}

export interface OAuthLoginRequest {
    provider: "GOOGLE" | "FACEBOOK" | "GITHUB";
    accessToken: string;
    deviceId: string;
    rememberMe?: boolean;
}

/**
 * public class AuthResponse {
    boolean requires2FA;
    List<MfaType> mfaTypes;
    MfaType defaultMfaType;
    UUID challengeId;
    String tokenType;
    String accessToken;
    Instant expiresIn;

    Boolean trustDevice;
    Boolean rememberMe;
    String deviceId;
}

 */
export interface AuthResponse {
    accessToken: string;
    requires2FA?: boolean;
    mfaTypes?: string[];
    defaultMfaType?: string;
    challengeId?: string;
    tokenType?: string;
    expiresIn?: string;
    trustDevice?: boolean;
    rememberMe?: boolean;
    deviceId?: string;
}


export interface TokenRefreshResponse {
    accessToken: string;
    refreshToken?: string;
}

export const authApi = {
    login: (payload: LoginRequest) =>
        apiClient.post<AuthResponse>("/auth/login", payload),
    oauthLogin: (payload: OAuthLoginRequest) =>
        apiClient.post<AuthResponse>("/auth/oauth", payload),
    refresh: () => apiClient.post<AuthResponse>("/auth/refresh"),
    me: () => apiClient.get<MeResponse>("/auth/me"),
    logout: () => apiClient.post("/auth/logout"),
    loginPasskeyStart: () => apiClient.post<StartPasskeyLoginResponse>("/auth/passkeys/login/start"),
    loginPasskeyFinish: (payload: FinishLoginPasskeyRequest) =>
        apiClient.post<AuthResponse>("/auth/passkeys/login/finish", payload),
    resetPassword: (email: string) =>
        apiClient.post<{ message: string }>("/auth/password/reset/request", { email }),
    confirmResetPassword: (token: string, newPassword: string) =>
        apiClient.post<{ message: string }>("/auth/password/reset/confirm", { token, newPassword }),
    verifyMfa: (challengeId: string, mfaType: string, code: string, trustDevice: boolean) =>
        apiClient.post<AuthResponse>("/auth/mfa/verify", { challengeId, mfaType, code, trustDevice }),
};
