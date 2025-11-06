import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import type { StaffScheduleTemplateRequest, StaffScheduleTemplateResponse } from "../models";
import { apiClient } from "../client";

export const StaffScheduleTemplateApi = {
    filter: (
        staffId: string,
        page: number,
        size: number,
        filterGroup?: FilterGroup | null,
        sorts?: SortRequest[],
    ) => apiClient.post<PageResponse<StaffScheduleTemplateResponse>>(`/admin/staffs/${staffId}/schedule-template/filter`, {
        page,
        size,
        filterGroup,
        sorts,
    }),
    create: async (staffId: string, data: StaffScheduleTemplateRequest) => {
        return apiClient.post<StaffScheduleTemplateResponse>(`/admin/staffs/${staffId}/schedule-template`, data);
    },
    update: async (scheduleId: string, data: Partial<StaffScheduleTemplateRequest>) => {
        return apiClient.put<StaffScheduleTemplateResponse>(`/admin/staffs/schedule-template/${scheduleId}`, data);
    },
    delete: async (id: string) => {
        return apiClient.delete<StaffScheduleTemplateResponse>(`/admin/staffs/schedule-template/${id}`);
    },
}