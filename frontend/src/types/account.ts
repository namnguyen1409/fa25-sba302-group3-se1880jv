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