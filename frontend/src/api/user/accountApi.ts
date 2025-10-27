import type { AccountSettingResponse, UserProfileResponse } from "@/types/account";
import { apiClient } from "../client";

export const AccountApi = {
    getUserProfile: () => apiClient.get<UserProfileResponse>("/account/profile"),
    updateUserProfile: (data: Partial<UserProfileResponse>) =>
        apiClient.put<UserProfileResponse>("/account/profile", data),
    updateAvatar: async (file: File) => {
        const formData = new FormData();
        formData.append("file", file);

        const response = await apiClient.post<UserProfileResponse>(
            "/account/profile/avatar",
            formData,
            {
                headers: {
                    "Content-Type": "multipart/form-data",
                },
            }
        );
        return response;
    },
    getAccountSettings: () => apiClient.get<AccountSettingResponse>("/account/settings"),
    updateAccountSettings: (data: any) =>
        apiClient.put("/account/settings", data),
}