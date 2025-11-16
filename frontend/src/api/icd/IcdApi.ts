import { type PageResponse, type FilterGroup, type SortRequest } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";
import { type IcdCodeResponse, type IcdCodeRequest } from "@/api";


export const IcdApi = {
    getIcd: (
        page: number,
        size: number,
        filterGroup?: FilterGroup,
        sorts?: SortRequest[],
        searchMode: string = "JPA"

    ) => apiClient.post<PageResponse<IcdCodeResponse>>("/common/icd-codes/filter", {
        page,
        size,
        filterGroup,
        sorts,
        searchMode
    }),
    SearchIcd: (keyWord: string) => apiClient.post<PageResponse<IcdCodeResponse>>("/common/icd-codes/filter", {
        page: 0,
        size: 10,
        filterGroup: {
            operator: "OR",
            filters: [
                {
                    field: "code",
                    operator: "containsIgnoreCase",
                    value: `${keyWord}`
                },
                {
                    field: "name",
                    operator: "containsIgnoreCase",
                    value: `${keyWord}`
                },
                {
                    field: "description",
                    operator: "containsIgnoreCase",
                    value: `${keyWord}`
                }
            ]
        },
        sorts: [],
        searchMode: "JPA"
    }),
    createIcd: (data: IcdCodeRequest) => 
        apiClient.post<IcdCodeResponse>("/common/icd-codes", data),
    updateIcd: (id: string, data: Partial<IcdCodeRequest>) => 
        apiClient.put<IcdCodeResponse>(`/common/icd-codes/${id}`, data),
    deleteIcd: (id: string) => 
        apiClient.delete<IcdCodeResponse>(`/common/icd-codes/${id}`),
    getIcdById: (id: string) => 
        apiClient.get<IcdCodeResponse>(`/common/icd-codes/${id}`),
}