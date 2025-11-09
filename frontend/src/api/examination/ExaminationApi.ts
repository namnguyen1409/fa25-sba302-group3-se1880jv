// src/api/examination/ExaminationApi.ts
import type { PageResponse } from "@/components/common/EntityTableWrapper"
import { apiClient } from "../client"
import type {
    ExaminationResponse,
    ExaminationRequest,
    VitalSignResponse,
    VitalSignRequest,
    ServiceOrderResponse,
    ServiceOrderRequest,
    PrescriptionResponse,
    PrescriptionRequest,
    DiagnosisRequest,
    CreateServiceOrderRequest,
} from "../models"

export const ExaminationApi = {
    getById: (id: string) =>
        apiClient.get<ExaminationResponse>(`/examinations/${id}`),

    update: (id: string, data: ExaminationRequest) =>
        apiClient.put<ExaminationResponse>(`/examinations/${id}`, data),

    // Vitals
    createVital: (id: string, data: VitalSignRequest) =>
        apiClient.post<VitalSignResponse>(`/examinations/${id}/vitals`, data),

    filterVitals: (
        id: string,
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    ) =>
        apiClient.post<PageResponse<VitalSignResponse>>(
            `/examinations/${id}/vitals/filter`,
            {
                page,
                size,
                filterGroup,
                sorts,
            }
        ),
    addVital: (id: string, data: VitalSignRequest) =>
        apiClient.post<VitalSignResponse>(`/examinations/${id}/vitals`, data),
    updateVital: (id: string, vitalId: string, data: VitalSignRequest) =>
        apiClient.put<VitalSignResponse>(`/examinations/${id}/vitals/${vitalId}`, data),

    // Diagnoses
    filterDiagnoses: (
        id: string,
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    ) =>
        apiClient.post<PageResponse<VitalSignResponse>>(
            `/examinations/${id}/diagnosis/filter`,
            {
                page,
                size,
                filterGroup,
                sorts,
            }
        ),
    createDiagnosis: (id: string, data: DiagnosisRequest) =>
        apiClient.post(`/examinations/${id}/diagnosis`, data),
    updateDiagnosis: (id: string, diagnosisId: string, data: any) =>
        apiClient.put(`/examinations/${id}/diagnosis/${diagnosisId}`, data),

    // Services

    filterServiceOrders: (
        id: string,
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    ) =>
        apiClient.post<PageResponse<ServiceOrderResponse>>(
            `/examinations/${id}/services/filter`,
            {
                page,
                size,
                filterGroup,
                sorts,
            }
        ),

    createServiceOrder: (id: string, data: CreateServiceOrderRequest) =>
        apiClient.post<ServiceOrderResponse[]>(`/examinations/${id}/services/orders`, data),

    getServiceOrders: (id: string) =>
        apiClient.get<ServiceOrderResponse[]>(`/examinations/${id}/services`),

    saveOrUpdateServiceOrder: (id: string, serviceOrderId: string, data: ServiceOrderRequest) =>
        apiClient.put<ServiceOrderResponse>(`/examinations/${id}/services/${serviceOrderId}`, data),
    // Prescription
    createPrescription: (id: string, data: PrescriptionRequest) =>
        apiClient.post<PrescriptionResponse>(`/examinations/${id}/prescription`, data),
    updatePrescription: (id: string, prescriptionId: string, data: PrescriptionRequest) =>
        apiClient.put<PrescriptionResponse>(`/examinations/${id}/prescription/${prescriptionId}`, data),

    deletePrescriptionItem: (id: string, prescriptionId: string) =>
        apiClient.delete(`/examinations/${id}/prescription/${prescriptionId}`),
    getPrescriptionItems: (prescriptionId: string, params: {
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    }) =>
        apiClient.post<PageResponse<PrescriptionResponse>>(
            `/prescriptions/${prescriptionId}/items/filter`,
            params
        ),

    createPrescriptionItem: (prescriptionId: string, data: any) =>
        apiClient.post<PrescriptionResponse>(`/prescriptions/${prescriptionId}/items`, data),

    updatePrescriptionItem: (itemId: string, data: any) =>
        apiClient.put<PrescriptionResponse>(`/prescriptions/items/${itemId}`, data),
        
    createLabOrder: (id: string, data: any) =>
        apiClient.post<ServiceOrderResponse[]>(`/examinations/${id}/lab/orders`, data),
    filterLabOrders: (
        id: string,
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    ) =>
        apiClient.post<PageResponse<ServiceOrderResponse>>(
            `/examinations/${id}/lab/orders/filter`,
            {
                page,
                size,
                filterGroup,
                sorts,
            }
        ),  
    /**
     * Filter / get paged examinations
     * @param page page index
     * @param size page size
     * @param filterGroup optional filter group
     * @param sorts optional sorts
     */
    getExaminations: (
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    ) =>
        apiClient.post<PageResponse<ExaminationResponse>>(
            `/examinations/filter`,
            {
                page,
                size,
                filterGroup,
                sorts,
            }
        ),
}
