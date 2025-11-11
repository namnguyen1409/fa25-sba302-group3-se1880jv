
import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";
import type { AllergyResponse, EmergencyContactResponse } from "@/api";


/**
 * "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
        "patientCode": "string",
        "fullName": "string",
        "dateOfBirth": "2025-11-01",
        "gender": "MALE",
        "bloodType": "A_POSITIVE",
        "status": "ACTIVE",
        "phone": "string",
        "email": "string",
        "address": "string",
        "insuranceNumber": "string",
        "initPassword": "string"

 */
export interface PatientResponse {
    id: string;
    patientCode: string;
    fullName: string;
    dateOfBirth: string;
    gender: string;
    bloodType: string;
    status: string;
    phone: string;
    email: string;
    address: string;
    insuranceNumber: string;
    initPassword: string;
}

export interface PatientRequest {
    fullName: string;
    dateOfBirth: string;
    gender: string;
    bloodType: string;
    status: string;
    phone: string;
    email: string;
    address: string;
    insuranceNumber: string;
}

export const PatientApi = {
    getPatient: (
        page: number,
        size: number,
        filterGroup?: FilterGroup,
        sorts?: SortRequest[],
        searchMode: string = "JPA"

    ) => apiClient.post<PageResponse<PatientResponse>>("/patients/filter", {
        page,
        size,
        filterGroup,
        sorts,
        searchMode
    }),
    search: (keyword: string, page: number = 0, size: number = 10) =>
        apiClient.post<PageResponse<PatientResponse>>("/patients/filter", {
            page,
            size,
            filterGroup: {
                operator: "OR",
                filters: [
                    { field: "fullName", operator: "containsIgnoreCase", value: keyword },
                    { field: "phone", operator: "containsIgnoreCase", value: keyword },
                    { field: "patientCode", operator: "containsIgnoreCase", value: keyword },
                ],
            },
            sorts: [{ field: "fullName", direction: "ASC" }],
        }),
    createPatient: (data: PatientRequest) =>
        apiClient.post<PatientResponse>("/patients", data),

    updatePatient: (patientId: string, data: PatientRequest) =>
        apiClient.put<PatientResponse>(`/patients/${patientId}`, data),

    deletePatient: (patientId: string) =>
        apiClient.delete<{ message: string }>(`/patients/${patientId}`),
    getPatientById: (patientId: string) =>
        apiClient.get<PatientResponse>(`/patients/${patientId}`),

    getEmergencyContactByPatientId: (patientId: string,
        page: number,
        size: number,
        filterGroup?: FilterGroup,
        sorts?: SortRequest[],
        searchMode: string = "JPA"
    ) => apiClient.post<PageResponse<EmergencyContactResponse>>(`/patients/${patientId}/emergency-contacts/filter`, {
        page,
        size,
        filterGroup,
        sorts,
        searchMode
    }),
    getEmergencyContactById: (patientId: string, contactId: string) =>
        apiClient.get<EmergencyContactResponse>(`/patients/${patientId}/emergency-contacts/${contactId}`),

    createEmergencyContact: (patientId: string, data: EmergencyContactResponse) =>
        apiClient.post<EmergencyContactResponse>(`/patients/${patientId}/emergency-contacts`, data),

    updateEmergencyContact: (patientId: string, contactId: string, data: EmergencyContactResponse) =>
        apiClient.put<EmergencyContactResponse>(`/patients/${patientId}/emergency-contacts/${contactId}`, data),

    deleteEmergencyContact: (patientId: string, contactId: string) =>
        apiClient.delete<{ message: string }>(`/patients/${patientId}/emergency-contacts/${contactId}`),
    getAllergiesByPatientId: (patientId: string,
        page: number,
        size: number,
        filterGroup?: FilterGroup,
        sorts?: SortRequest[],
        searchMode: string = "JPA"
    ) => apiClient.post<PageResponse<AllergyResponse>>(`/patients/${patientId}/allergies/filter`, {
        page,
        size,
        filterGroup,
        sorts,
        searchMode
    }),
    getAllergyById: (patientId: string, allergyId: string) =>
        apiClient.get<AllergyResponse>(`/patients/${patientId}/allergies/${allergyId}`),

    createAllergy: (patientId: string, data: AllergyResponse) =>
        apiClient.post<AllergyResponse>(`/patients/${patientId}/allergies`, data),

    updateAllergy: (allergyId: string, data: AllergyResponse) =>
        apiClient.put<AllergyResponse>(`/patients/allergies/${allergyId}`, data),

    deleteAllergy: (allergyId: string) =>
        apiClient.delete<{ message: string }>(`/patients/allergies/${allergyId}`),
};