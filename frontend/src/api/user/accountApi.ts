import { type AccountSettingResponse, type DeviceSessionResponse, type MfaConfigResponse, type TOTPSetupResponse, type UserProfileResponse } from "@/types/account";
import { apiClient } from "../client";
import type { Filter, FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import type { LoginAttemptResponse } from "@/pages/account/loginAction";

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
    updateUsername: (username: string) =>
        apiClient.patch<AccountSettingResponse>("/account/settings/username", { newUsername: username }),
    requestEmailChange: (newEmail: string) =>
        apiClient.patch<{ message: string }>("/account/settings/email/request-change", { newEmail }),
    confirmEmailChange: (token: string) =>
        apiClient.get<{ message: string }>("/account/settings/email/verify-change", { params: { token } }),
    getMfaMethods: () => apiClient.get<MfaConfigResponse[]>("/account/security/mfa"),
    requestTOTP: () => apiClient.get<TOTPSetupResponse>("/account/security/mfa/totp"),
    confirmTOTP: (code: string, secret: string) =>
        apiClient.post<MfaConfigResponse>("/account/security/mfa/totp", { code, secret }),
    resetPassword: () =>
        apiClient.post<{ message: string }>("/account/security/password/reset"),
    disableMFA: (verificationMethod: string, code: string) =>
        apiClient.delete<{ message: string }>(`/account/security/mfa/disable`, { verificationMethod, code }),
    enableMFA: () =>
        apiClient.post<{ message: string }>("/account/security/mfa/enable"),
    startRegistration: () =>
        apiClient.post<any>("/account/security/mfa/passkey/registration/start"),
    finishRegistration: (credentialJson: string) =>
        apiClient.post<void>("/account/security/mfa/passkey/registration/finish", { credential: credentialJson }),
    initEmailMfa: (email: string) =>
        apiClient.post<{ challengeId: string }>("/account/security/mfa/email/init", { email }),
    confirmEmailMfa: (challengeId: string, code: string) =>
        apiClient.post("/account/security/mfa/email/confirm", { challengeId, code }),
    deleteMfaConfig: (configId: string, verificationMethod: string, code: string) =>
        apiClient.post("/account/security/mfa/delete", { configId, verificationMethod, code }),
    generateBackupCodes: () =>
        apiClient.get<string[]>("/account/security/mfa/backup-codes"),
    changePassword: (currentPassword: string, newPassword: string) =>
        apiClient.post("/account/security/password/change", { currentPassword, newPassword }),
    getDevices: () => apiClient.get<DeviceSessionResponse[]>("/account/devices"),
    revokeDevice: (deviceId: string) =>
        apiClient.post<{ message: string }>(`/account/devices/logout/${deviceId}`),
    revokeAllDevices: () =>
        apiClient.post<{ message: string }>("/account/devices/logout-all"),

    getLoginHistory: (
        page: number,
        size: number,
        filterGroup?: FilterGroup,
        sorts?: SortRequest[],
        searchMode: string = "JPA"

    ) => apiClient.post<PageResponse<LoginAttemptResponse>>("/account/login-activity/filter", {
        page,
        size,
        filterGroup,
        sorts,
        searchMode
    }),
};