import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import type { LabTestRequest, LabTestResponse } from "@/api";
import { apiClient } from "../client";
export const LabTestApi = {
    filter: (page: number, size: number, filterGroup?: FilterGroup, sorts?: SortRequest[]) => 
        apiClient.post<PageResponse<LabTestResponse>>("/lab-tests/filter", {
            page,
            size,
            filterGroup,
            sorts
        }),
    search: (keyword: string, page=0, size=10) => 
        apiClient.post<PageResponse<LabTestResponse>>("/lab-tests/filter", {
            page,
            size,
            filterGroup: {
                operator: "OR",
                filters: [
                    {
                        field: "code",
                        operator: "containsIgnoreCase",
                        value: `${keyword}`
                    },
                    {
                        field: "name",
                        operator: "containsIgnoreCase",
                        value: `${keyword}`
                    },
                    {
                        field: "description",
                        operator: "containsIgnoreCase",
                        value: `${keyword}`
                    }
                ]
            },
            sorts: [],
            searchMode: "JPA"
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