import { apiClient } from "../client";
import type { LabOrderResponse } from "@/api";
import { type PageResponse } from '@/components/common/EntityTableWrapper';

export const LabOrderApi = {
    filter: (
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    ) =>
        apiClient.post<PageResponse<LabOrderResponse>>(`/lab-orders/filter`, {
            page,
            size,
            filterGroup,
            sorts,
        }),

    getOrdersForStaffToday: () =>
        apiClient.get<LabOrderResponse[]>(`/lab-orders/staff/today`),
    update: (id: string, data: Partial<LabOrderResponse>) =>
        apiClient.put<LabOrderResponse>(`/lab-orders/${id}`, data),
    getById: (id: string) =>
        apiClient.get<LabOrderResponse>(`/lab-orders/${id}`),
    updateItem: (itemId: string, data: any) =>
        apiClient.put(`/lab-order-items/${itemId}`, data),

}