import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import type { LabTestRequest, LabTestResponse } from "../models";
import { apiClient } from "../client";

export const LabTestApi = {
    filter: (page: number, size: number, filterGroup?: FilterGroup, sorts?: SortRequest[]) => 
        apiClient.post<PageResponse<LabTestResponse>>("/lab-tests/filter", {
            page,
            size,
            filterGroup,
            sorts
        }),
    create: (data: LabTestRequest) =>
        apiClient.post<LabTestResponse>("/lab-tests", data),
    update: (id: string, data: Partial<LabTestRequest>) =>
        apiClient.put<LabTestResponse>(`/lab-tests/${id}`, data),
    delete: (id: string) =>
        apiClient.delete<LabTestResponse>(`/lab-tests/${id}`),
    getById: (id: string) =>
        apiClient.get<LabTestResponse>(`/lab-tests/${id}`),
}   