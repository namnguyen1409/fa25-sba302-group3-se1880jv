import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";
import type { StaffRequest, StaffResponse } from "../models";

export const StaffApi = {
    getStaff : (
        page: number,
        size: number,
        filterGroup?: FilterGroup,
        sorts?: SortRequest[],
    ) => apiClient.post<PageResponse<StaffResponse>>("/admin/staffs/filter", {
        page,
        size,
        filterGroup,
        sorts,
    }),
    createStaff: (data: StaffRequest) => 
        apiClient.post<StaffResponse>("/admin/staffs", data),
    updateStaff: (id: string, data: Partial<StaffRequest>) => 
        apiClient.put<StaffResponse>(`/admin/staffs/${id}`, data),
    deleteStaff: (id: string) => 
        apiClient.delete<StaffResponse>(`/admin/staffs/${id}`),
    getStaffById: (id: string) => 
        apiClient.get<StaffResponse>(`/admin/staffs/${id}`),
    resetPassword: (id: string) =>
        apiClient.post<void>(`/admin/staffs/${id}/reset-password`),
}