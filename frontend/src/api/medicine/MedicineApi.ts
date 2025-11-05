import { apiClient } from "../client";
import type { PageResponse, FilterGroup, SortRequest } from "@/components/common/EntityTableWrapper";
import type { MedicineRequest, MedicineResponse } from "../models";

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

  createMedicine: (data: MedicineRequest) =>
    apiClient.post<MedicineResponse>("/medicines", data),

  updateMedicine: (id: string, data: Partial<MedicineRequest>) =>
    apiClient.put<MedicineResponse>(`/medicines/${id}`, data),

  deleteMedicine: (id: string) =>
    apiClient.delete(`/medicines/${id}`),
}
