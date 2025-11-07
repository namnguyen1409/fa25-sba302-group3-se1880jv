import { apiClient } from "../client";
import type { ServiceOrderResponse } from "../models";

export const ServiceOrderApi = {

    getOrdersForStaffToday: () =>
        apiClient.get<ServiceOrderResponse[]>(`/service-orders/staff/today`),
    update: (id: string, data: Partial<ServiceOrderResponse>) =>
        apiClient.put<ServiceOrderResponse>(`/service-orders/${id}`, data),
    getById: (id: string) =>
        apiClient.get<ServiceOrderResponse>(`/service-orders/${id}`),
    updateItem: (itemId: string, data: any) =>
        apiClient.put(`/service-order-items/${itemId}`, data),

}