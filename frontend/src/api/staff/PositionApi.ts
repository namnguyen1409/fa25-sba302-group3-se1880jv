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
    search: (keyword: string) => apiClient.post<PageResponse<PositionResponse>>("/admin/positions/filter", {
        page: 0,
        size: 10,
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