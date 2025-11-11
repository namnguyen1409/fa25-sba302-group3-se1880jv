import { apiClient } from "../client";
import type { PageResponse, FilterGroup, SortRequest } from "@/components/common/EntityTableWrapper";
import type { MedicineRequest, MedicineResponse } from "@/api";

export const MedicineApi = {
  getMedicines: (
    page: number,
    size: number,
    filterGroup?: FilterGroup,
    sorts?: SortRequest[]
  ) =>
    apiClient.post<PageResponse<MedicineResponse>>("/medicines/filter", {
      page,
      size,
      filterGroup,
      sorts,
    }),
  search: (keyword: string, page: number = 0, size: number = 10) =>
    apiClient.post<PageResponse<MedicineResponse>>("/medicines/filter", {
      page,
      size,
      filterGroup: {
        operator: "OR",
        filters: [
          {
            field: "code",
            operator: "containsIgnoreCase",
            value: `${keyword}`,
          },
          {
            field: "name",
            operator: "containsIgnoreCase",
            value: `${keyword}`,
          },
          {
            field: "description",
            operator: "containsIgnoreCase",
            value: `${keyword}`,
          },
        ],
      },
      sorts: [],
    }),
  createMedicine: (data: MedicineRequest) =>
    apiClient.post<MedicineResponse>("/medicines", data),

  updateMedicine: (id: string, data: Partial<MedicineRequest>) =>
    apiClient.put<MedicineResponse>(`/medicines/${id}`, data),

  deleteMedicine: (id: string) =>
    apiClient.delete(`/medicines/${id}`),
}
