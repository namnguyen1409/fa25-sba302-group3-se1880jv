import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import type { StaffScheduleDayOffRequest, StaffScheduleGenerateRequest, StaffScheduleRequest, StaffScheduleResponse } from "../models";
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
     getRange: (staffId: string, from: string, to: string) =>
        apiClient.get<StaffScheduleResponse[]>(
            `/admin/staffs/${staffId}/schedule?from=${from}&to=${to}`
        ),

    /** Generate schedule từ template */
    generate: (staffId: string, body?: StaffScheduleGenerateRequest) =>
        apiClient.post(`/admin/staffs/${staffId}/schedule/generate`, body),

    /** Đánh dấu trạng thái OFF/BLOCKED/CANCELLED cho 1 slot */
    markStatus: (
        scheduleId: string,
        status: string,
        note?: string
    ) => {
        const q = new URLSearchParams({ status });
        if (note) q.append("note", note);
        return apiClient.patch<StaffScheduleResponse>(
            `/admin/staffs/schedule/${scheduleId}/status?${q.toString()}`
        );
    },

    /** Tạo Day-Off (override 1 slot) */
    dayOff: (body: StaffScheduleDayOffRequest) =>
        apiClient.post<StaffScheduleResponse>(`/admin/staffs/schedule/day-off`, body),
}