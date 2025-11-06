import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import type { StaffScheduleRequest, StaffScheduleResponse } from "../models";
import { apiClient } from "../client";

export const StaffScheduleApi = {
    filter: (
        staffId: string,
        page: number,
        size: number,
        filterGroup?: FilterGroup | null,
        sorts?: SortRequest[],
    ) => apiClient.post<PageResponse<StaffScheduleResponse>>(`/admin/staffs/${staffId}/schedule/filter`, {
        page,
        size,
        filterGroup,
        sorts,
    }),
    create: async (staffId: string, data: StaffScheduleRequest) => {
        return apiClient.post<StaffScheduleResponse>(`/admin/staffs/${staffId}/schedule`, data);
    },
    update: async (scheduleId: string, data: Partial<StaffScheduleRequest>) => {
        return apiClient.put<StaffScheduleResponse>(`/admin/staffs/schedule/${scheduleId}`, data);
    },
    delete: async (id: string) => {
        return apiClient.delete<StaffScheduleResponse>(`/admin/staffs/schedule/${id}`);
    },
}