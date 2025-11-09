import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";
import type { ServiceCatalogRequest, ServiceCatalogResponse } from "../models";

export const ServiceCatalogApi = {
    filter: (page: number, size: number, filterGroup?: FilterGroup, sorts?: SortRequest[]) =>
        apiClient.post<PageResponse<ServiceCatalogResponse>>("/examinations/service-catalog/filter", {
            page,
            size,
            filterGroup,
            sorts
        }),
    search: (keyword: string, page: number = 0, size: number = 10) =>
        apiClient.post<PageResponse<ServiceCatalogResponse>>(`/examinations/service-catalog/filter`, {
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
    create: (data: ServiceCatalogRequest) =>
        apiClient.post<ServiceCatalogResponse>("/examinations/service-catalog", data),
    update: (id: string, data: Partial<ServiceCatalogRequest>) =>
        apiClient.put<ServiceCatalogResponse>(`/examinations/service-catalog/${id}`, data),
    delete: (id: string) =>
        apiClient.delete<ServiceCatalogResponse>(`/examinations/service-catalog/${id}`),
    getById: (id: string) =>
        apiClient.get<ServiceCatalogResponse>(`/examinations/service-catalog/${id}`),
}