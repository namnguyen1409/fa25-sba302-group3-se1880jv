
import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";


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

/**
 * "fullName": "string",
  "dateOfBirth": "2025-11-01",
  "gender": "MALE",
  "bloodType": "A_POSITIVE",
  "status": "ACTIVE",
  "phone": "+84526875839",
  "email": "string",
  "address": "string",
  "insuranceNumber": "string"
 */

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
    getPatient : (
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

    createPatient: (data: PatientRequest) =>
        apiClient.post<PatientResponse>("/patients", data),

    updatePatient: (patientId: string, data: PatientRequest) =>
        apiClient.put<PatientResponse>(`/patients/${patientId}`, data),

    deletePatient: (patientId: string) =>
        apiClient.delete<{ message: string }>(`/patients/${patientId}`),
}