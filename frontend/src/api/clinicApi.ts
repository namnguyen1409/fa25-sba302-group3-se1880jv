import { apiClient } from "./client";
import type { ClinicResponse } from "./models";

export const clinicApi = {
    getClinicInfo: () => apiClient.get<ClinicResponse>("/organization/clinics")
};