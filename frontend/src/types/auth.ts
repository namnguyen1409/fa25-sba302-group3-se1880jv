export interface UserProfile {
  id: string;
  username: string;
  fullName?: string;
  email?: string;
  roles: ("ADMIN" | "DOCTOR" | "NURSE" | "RECEPTIONIST" | "PATIENT")[];
  permissions?: string[];
  mfaEnabled?: boolean;
  trustedDevice?: boolean;
}

export interface AuthResponse {
  requires2FA: boolean;
  accessToken: string;
  tokenType: "Bearer";
  expiresIn: string;
}

export interface LoginRequest {
  username: string;
  password: string;
  deviceId: string;
  rememberMe?: boolean;
}
