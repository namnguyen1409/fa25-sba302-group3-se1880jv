import axiosInstance from "./axiosInstance";

export const apiClient = {
  get: async <T>(url: string, config?: any): Promise<T> => {
    const res = await axiosInstance.get<T>(url, config);
    return res as T;
  },

  post: async <T>(url: string, data?: any, config?: any): Promise<T> => {
    const res = await axiosInstance.post<T>(url, data, config);
    return res as T;
  },

  put: async <T>(url: string, data?: any, config?: any): Promise<T> => {
    const res = await axiosInstance.put<T>(url, data, config);
    return res as T;
  },

  patch: async <T>(url: string, data?: any, config?: any): Promise<T> => {
    const res = await axiosInstance.patch<T>(url, data, config);
    return res as T;
  },

  delete: async <T>(url: string, config?: any): Promise<T> => {
    const res = await axiosInstance.delete<T>(url, config);
    return res as T;
  },
};
