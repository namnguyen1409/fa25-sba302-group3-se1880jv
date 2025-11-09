import { apiClient } from "./client";
import type { ClinicResponse } from "./models";

export const clinicApi = {
    getClinicInfo: () => apiClient.get<ClinicResponse>("/organization/clinics"),
    updateClinic: (id: string, data: Partial<ClinicResponse>) => apiClient.put<ClinicResponse>(`/organization/clinics/${id}`, data)
};