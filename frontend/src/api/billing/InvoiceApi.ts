import { type PageResponse } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";
import type { InvoiceResponse } from "@/api";

export const InvoiceApi = {
    getInvoicesForStaffToday: () =>
        apiClient.get<InvoiceResponse[]>(`/invoices/staff/today`),
    update: (id: string, data: Partial<InvoiceResponse>) =>
        apiClient.put<InvoiceResponse>(`/invoices/${id}`, data),
    getById: (id: string) =>
        apiClient.get<InvoiceResponse>(`/invoices/${id}`),
    filter: (page: number, size: number, filterGroup?: any, sorts?: any) =>
        apiClient.post<PageResponse<InvoiceResponse>>(
            `/invoices/filter`,
            {
                page,
                size,
                filterGroup,
                sorts,
            }
        ),
}