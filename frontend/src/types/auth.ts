export interface MeResponse {
  id: string;
  username: string;
  email: string;
  phone?: string | null;
  active: boolean;
  locked: boolean;
  mfaEnabled: boolean;
  firstLogin: boolean;
  userProfile: {
    fullName: string;
    avatarUrl?: string | null;
  };
  roles: {
    name: string;
    permissions: { name: string }[];
  }[];
  device?: {
    deviceId: string;
    trusted: boolean;
    lastLoginAt?: string;
  };
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
