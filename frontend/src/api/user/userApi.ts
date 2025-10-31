import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";

export interface PermissionDto {
  name: string;
}

export interface RoleDto {
  name: string;
  permissions: PermissionDto[];
}

export interface UserProfileDto {
  fullName: string;
  avatarUrl?: string;
}

export interface UserResponse {
  id: string;
  username: string;
  email: string;
  active: boolean;
  locked: boolean;
  mfaEnabled: boolean;
  firstLogin: boolean;

  roles: RoleDto[];
  userProfile: UserProfileDto;
}


export const UserApi = {
    getUser: (
        page: number,
        size: number,
        filterGroup?: FilterGroup,
        sorts?: SortRequest[],
        searchMode: string = "JPA"

    ) => apiClient.post<PageResponse<UserResponse>>("/admin/users/filter", {
        page,
        size,
        filterGroup,
        sorts,
        searchMode
    }),
    lockUser: (userId: string) =>
        apiClient.post<{ message: string }>(`/admin/users/${userId}/lock`),
    unlockUser: (userId: string) =>
        apiClient.post<{ message: string }>(`/admin/users/${userId}/unlock`),
}