export interface UserProfileResponse {
  id: string;
  createdDate: string;
  lastModifiedDate: string;
  userUsername: string;
  userEmail: string;
  userPhone: string;
  fullName: string;
  dateOfBirth: string;
  address: {
    street: string;
    wardName: string;
    districtName: string;
    city: string;
  };
  avatarUrl: string;
}