import type { CustomApiResponse } from "@/types/base.dto";
import axiosInstance from "./axiosInstance";
import { apiClient } from "./client";
import type { UserProfile } from "@/types/auth";

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

export interface AuthResponse {
    accessToken: string;
    refreshToken?: string;
    user?: {
        id: string;
        username: string;
        email: string;
        role: string;
    };
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
    me: () => apiClient.get<UserProfile>("/auth/me"),
    logout: () => apiClient.post("/auth/logout"),
};
