import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";
import type { AppointmentRequest, AppointmentResponse } from "@/api";

export const AppointmentApi = {
  getAppointments: (
    page: number,
    size: number,
    filterGroup?: FilterGroup | null,
    sorts?: SortRequest[],
    searchMode = "JPA"
  ) =>
    apiClient.post<PageResponse<AppointmentResponse>>("/appointments/filter", {
      page,
      size,
      filterGroup,
      sorts,
      searchMode,
    }),

  createAppointment: (data: AppointmentRequest) =>
    apiClient.post<AppointmentResponse>("/appointments", data),
};