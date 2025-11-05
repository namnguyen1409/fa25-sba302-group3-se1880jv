import type { FilterGroup, PageResponse, SortRequest } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";
import type { RoomRequest, RoomResponse } from "../models";

export const RoomApi = {
    getRooms: (
        page: number,
        size: number,
        filterGroup?: FilterGroup,
        sorts?: SortRequest[],
        searchMode: string = "JPA"
    ) => apiClient.post<PageResponse<RoomResponse>>("/organization/rooms/filter", {
        page,
        size,
        filterGroup,
        sorts,
        searchMode
    }),
    createRoom: (data: RoomRequest) =>
        apiClient.post<RoomResponse>("/organization/rooms", data),
    updateRoom: (id: string, data: Partial<RoomRequest>) =>
        apiClient.put<RoomResponse>(`/organization/rooms/${id}`, data),
    deleteRoom: (id: string) =>
        apiClient.delete<RoomResponse>(`/organization/rooms/${id}`),
    getRoomById: (id: string) =>
        apiClient.get<RoomResponse>(`/organization/rooms/${id}`),
}