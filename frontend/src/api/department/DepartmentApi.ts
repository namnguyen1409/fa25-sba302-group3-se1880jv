import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import type { DepartmentRequest, DepartmentResponse } from "../models";
import { apiClient } from "../client";

export const DepartmentApi = {
    search: (keyword: string, page: number = 0, size: number = 10) => apiClient.post<PageResponse<DepartmentResponse>>("/organization/departments/filter", {
        page,
        size,
        filterGroup: {
            operator: "AND",
            filters: [
                {
                    field: "name",
                    operator: "containsIgnoreCase",
                    value: keyword
                }
            ]
        },
        sorts: [
            {
                field: "name",
                direction: "ASC"
            }
        ]
    }),
    getDepartments: (
        page: number,
        size: number,
        filterGroup?: FilterGroup,
        sorts?: SortRequest[],
        searchMode: string = "JPA" 
    ) => apiClient.post<PageResponse<DepartmentResponse>>("/organization/departments/filter", {
        page,
        size,
        filterGroup,
        sorts,
        searchMode
    }),
    createDepartment: (data: DepartmentRequest) => 
        apiClient.post<DepartmentResponse>("/organization/departments", data),
    updateDepartment: (id: string, data: Partial<DepartmentRequest>) => 
        apiClient.put<DepartmentResponse>(`/organization/departments/${id}`, data),
    deleteDepartment: (id: string) => 
        apiClient.delete<DepartmentResponse>(`/organization/departments/${id}`),
    getDepartmentById: (id: string) => 
        apiClient.get<DepartmentResponse>(`/organization/departments/${id}`),
}