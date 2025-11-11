import type { PageResponse } from "@/components/common/EntityTableWrapper";
import { apiClient } from "../client";
import type { ContentResponse } from "@/api";

export const ContentApi = {
    filter: (
        page: number,
        size: number,
        filterGroup?: any,
        sorts?: any
    ) => apiClient.post<PageResponse<ContentResponse>>(
        `/cms/contents/filter`,
        {
            page,
            size,
            filterGroup,
            sorts
        }
    ),
    getById: (id: string) =>
        apiClient.get<ContentResponse>(`/cms/contents/${id}`),

    getBySlug: (slug: string) =>
        apiClient.get<ContentResponse>(`/cms/contents/slug/${slug}`),

    create: (data: ContentResponse) =>
        apiClient.post<ContentResponse>(`/cms/contents`, data),

    update: (id: string, data: ContentResponse) =>
        apiClient.put<ContentResponse>(`/cms/contents/${id}`, data),

    delete: (id: string) =>
        apiClient.delete<void>(`/cms/contents/${id}`),
    publish: (id: string) =>
        apiClient.post<void>(`/cms/contents/${id}/publish`),
    archive: (id: string) =>
        apiClient.post<void>(`/cms/contents/${id}/archive`),
}