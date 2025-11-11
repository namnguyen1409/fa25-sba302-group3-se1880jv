import type { PageResponse } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";
import type { ServiceOrderResponse } from "@/api";

export const ServiceOrderApi = {

    getOrdersForStaffToday: () =>
        apiClient.get<ServiceOrderResponse[]>(`/service-orders/staff/today`),
    update: (id: string, data: Partial<ServiceOrderResponse>) =>
        apiClient.put<ServiceOrderResponse>(`/service-orders/${id}`, data),
    getById: (id: string) =>
        apiClient.get<ServiceOrderResponse>(`/service-orders/${id}`),
    updateItem: (itemId: string, data: any) =>
        apiClient.put(`/service-order-items/${itemId}`, data),
    filter: (
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    ) =>
        apiClient.post<PageResponse<ServiceOrderResponse>>(
            `/service-orders/filter`,
            {
                page,
                size,
                filterGroup,
                sorts,
            }
        ),

}