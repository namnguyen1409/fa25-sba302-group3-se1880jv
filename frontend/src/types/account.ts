export interface UserProfileResponse {
  id: string;
  createdDate: string;
  lastModifiedDate: string;
  userUsername: string;
  userEmail: string;
  phone: string;
  fullName: string;
  dateOfBirth: string;
  address: {
    street: string;
    wardName: string;
    city: string;
  };
  avatarUrl: string;
}

export interface AccountSettingResponse {
  username: string; 
  email: string;
  active: boolean;
  mfaEnabled: boolean;
  firstLogin: boolean;
  OAuthAccounts?: {
    createdDate: string;
    provider: "GOOGLE" | "FACEBOOK" | "GITHUB";
    email: string;
    name: string;
    avatarUrl?: string;
    isRevoke: boolean;
  }[];
}
export interface MfaConfigResponse {
  id: string;
  createdDate: string;
  mfaType: "TOTP" | "EMAIL" | "PASSKEY" | "SMS";
  contact?: string;
  primary: boolean;
  lastVerifiedAt?: string;
  deviceName?: string;
  revoked?: boolean;
}

export interface TOTPSetupResponse {
  secret: string;
  qrUri: string;
}

/**
 *  "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
      "createdBy": "string",
      "deviceName": "string",
      "ipAddress": "string",
      "userAgent": "string",
      "trusted": true,
      "expiresIn": "2025-10-28T01:00:52.723Z",
      "revoked": true,
      "rememberMe": true,
      "lastLoginAt": "2025-10-28T01:00:52.723Z"
 */

export interface DeviceSessionResponse {
  id: string;
  createdBy: string;
  deviceName: string;
  ipAddress: string;
  userAgent?: string;
  trusted: boolean;
  expiresIn: string;
  revoked: boolean;
  rememberMe: boolean;
  lastLoginAt: string;
}