import type { PageResponse } from "@/components/common/EntityTableWrapper";
import type { LabTestResultResponse } from "../models";
import { apiClient } from "../client";

export const LabResultApi = {

    filterByLabOrder: (
        labOrderId: string,
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    ) =>
        apiClient.post<PageResponse<LabTestResultResponse>>(
            `/lab-orders/${labOrderId}/results/filter`,
            {
                page,
                size,
                filterGroup,
                sorts,
            }
        ),
        update: (itemId: string, data: any) =>
            apiClient.put(`/lab-orders/results/${itemId}`, data),
        /**
         * Verify a lab test result. POST /lab-orders/result/{itemId}/verify (no body)
         */
        verify: (itemId: string) =>
            apiClient.post<void>(`/lab-orders/result/${itemId}/verify`),

}