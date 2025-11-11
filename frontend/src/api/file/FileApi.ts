import { apiClient } from "../client";

export const FileApi = {
  upload: async ({
    file,
    entityType,
    entityId,
  }: {
    file: File;
    entityType: string;
    entityId: string;
  }) => {
    const form = new FormData();
    form.append("file", file);
    form.append("entityType", entityType);
    form.append("entityId", entityId);

    const res = await apiClient.post<string>("/files/upload", form, {
      headers: { "Content-Type": "multipart/form-data" },
    });

    return res;
  },
  
   getByEntity: async (entityType: string, entityId: string) => {
    return apiClient.get<any>(`/files`, {
      params: { entityType, entityId },
    });
  },
};
