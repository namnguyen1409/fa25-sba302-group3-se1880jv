import type { UserProfileResponse } from "@/types/account";
import { apiClient } from "../client";

export const AccountApi = {
    getUserProfile: () => apiClient.get<UserProfileResponse>("/account/profile"),
}