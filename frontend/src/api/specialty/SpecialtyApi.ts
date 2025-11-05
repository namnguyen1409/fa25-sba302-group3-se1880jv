
import { apiClient } from "../client";
import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import type { SpecialtyRequest, SpecialtyResponse } from "../models";


export const SpecialtyApi = {
  getSpecialties: (
    page: number,
    size: number,
    filterGroup?: FilterGroup,
    sorts?: SortRequest[],
    searchMode: string = "JPA"
  ) =>
    apiClient.post<PageResponse<SpecialtyResponse>>(
      "/admin/specialties/filter",
      { page, size, filterGroup, sorts, searchMode }
    ),

  search: (keyword: string) =>
    apiClient.post<PageResponse<SpecialtyResponse>>("/admin/specialties/filter", {
      page: 0,
      size: 10,
      filterGroup: {
        operator: "AND",
        filters: [
          { field: "name", operator: "containIgnoreCase", value: keyword }
        ]
      }
    }),

  create: (data: SpecialtyRequest) =>
    apiClient.post("/admin/specialties", data),

  update: (id: string, data: Partial<SpecialtyRequest>) =>
    apiClient.put(`/admin/specialties/${id}`, data),

  delete: (id: string) =>
    apiClient.delete(`/admin/specialties/${id}`),

  getById: (id: string) =>
    apiClient.get<SpecialtyResponse>(`/admin/specialties/${id}`),
};
