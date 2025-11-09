import { apiClient } from "../client";
import type { InvoiceResponse } from "../models";

export const InvoiceApi = {
    getInvoicesForStaffToday: () =>
        apiClient.get<InvoiceResponse[]>(`/invoices/staff/today`),
    update: (id: string, data: Partial<InvoiceResponse>) =>
        apiClient.put<InvoiceResponse>(`/invoices/${id}`, data),
    getById: (id: string) =>
        apiClient.get<InvoiceResponse>(`/invoices/${id}`),
}