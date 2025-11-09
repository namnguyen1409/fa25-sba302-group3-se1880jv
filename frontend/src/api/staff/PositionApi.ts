import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import type { PositionRequest, PositionResponse } from "../models";
import { apiClient } from "../client";

export const PositionApi = {
    filter: (
        page: number,
        size: number,
        filterGroup?: FilterGroup,
        sorts?: SortRequest[],
    ) => apiClient.post<PageResponse<PositionResponse>>("/admin/positions/filter", {
        page,
        size,
        filterGroup,
        sorts,
    }),
    search: (keyword: string, page: number = 0, size: number = 10) => apiClient.post<PageResponse<PositionResponse>>("/admin/positions/filter", {
        page,
        size,
        filterGroup: {
            operator: "AND",
            filters: [
                {
                    field: "title",
                    operator: "containsIgnoreCase",
                    value: keyword
                }
            ]
        },
        sorts: [
            {
                field: "title",
                direction: "ASC"
            }
        ]
    }),
    createPosition: (data: PositionRequest) =>
        apiClient.post<PositionResponse>("/admin/positions", data),
    updatePosition: (id: string, data: Partial<PositionRequest>) =>
        apiClient.put<PositionResponse>(`/admin/positions/${id}`, data),
    deletePosition: (id: string) =>
        apiClient.delete<PositionResponse>(`/admin/positions/${id}`),
    getPositionById: (id: string) =>
        apiClient.get<PositionResponse>(`/admin/positions/${id}`),
}