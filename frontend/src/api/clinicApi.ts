import type { ClinicInfo, Department, Specialty } from "@/types/clinic";
import { apiClient } from "./client";

export const clinicApi = {
    getClinicInfo: () => apiClient.get<ClinicInfo>("/clinic/info"),
    getDepartments: () => apiClient.get<Department[]>("/clinic/departments"),
    getSpecialties: () => apiClient.get<Specialty[]>("/clinic/specialties"),
};