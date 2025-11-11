
import { apiClient } from "../client";
import type { DispenseRecordResponse } from "@/api";
import type { PageResponse } from "@/components/common/EntityTableWrapper";

export const PharmacyApi = {
    getDispenseRecordsToday: () =>
        apiClient.get<DispenseRecordResponse[]>(`/dispense-records/staff/today`),
    markAsDispensed: (id: string) =>
        apiClient.put<DispenseRecordResponse>(`/dispense-records/${id}`, {
            status: "DISPENSED",
            dispensedAt: new Date().toISOString(),
        }),
    getDispenseRecord: (id: string) =>
        apiClient.get<DispenseRecordResponse>(`/dispense-records/${id}`),
    filter: (
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    ) =>
        apiClient.post<PageResponse<DispenseRecordResponse>>(
            `dispense-records/filter`,
            {
                page,
                size,
                filterGroup,
                sorts,
            }
        ),  
}