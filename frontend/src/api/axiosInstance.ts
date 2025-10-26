import axios from "axios";
import type { AxiosInstance, AxiosRequestConfig, AxiosResponse, AxiosError, InternalAxiosRequestConfig } from "axios";
import { ensureDeviceId } from "@/utils/device";
import { toast } from "sonner";

export interface CustomApiResponse<T> {
  code: number;
  message?: string;
  data: T;
  timestamp?: string;
  path?: string;
}

export interface TokenResponse {
  accessToken: string;
}

const getAccessToken = () => localStorage.getItem("accessToken");


const API_BASE_URL = import.meta.env.VITE_API_BASE_URL || "http://localhost:9999/api";

const axiosInstance: AxiosInstance = axios.create({
  baseURL: API_BASE_URL,
  timeout: 15000,
  withCredentials: true,
  headers: { "Content-Type": "application/json" },
});

axiosInstance.interceptors.request.use(
  (config: InternalAxiosRequestConfig) => {
    const token = getAccessToken();
    const deviceId = ensureDeviceId();

    if (token && config.headers) {
      config.headers["Authorization"] = `Bearer ${token}`;
    }
    if (config.headers) {
      config.headers["X-Device-Id"] = deviceId;
    }

    return config;
  },
  (error) => Promise.reject(error)
);

let isRefreshing = false;
let failedQueue: {
  resolve: (token: string | null) => void;
  reject: (err: any) => void;
}[] = [];

const processQueue = (error: any, token: string | null = null) => {
  failedQueue.forEach((prom) => {
    if (error) prom.reject(error);
    else prom.resolve(token);
  });
  failedQueue = [];
};

function isCustomApiResponse(obj: any): obj is CustomApiResponse<any> {
  return (
    obj &&
    typeof obj === "object" &&
    "code" in obj &&
    "message" in obj &&
    "path" in obj
  );
}


axiosInstance.interceptors.response.use(
  (response: AxiosResponse<CustomApiResponse<any>>) => {
    console.log("API response:", response);
    const res = response.data;
    if (res && typeof res === "object" && "code" in res) {
      if (res.code === 5001) {
        return Promise.reject({
          isTokenExpired: true,
          originalResponse: res,
          config: response.config,
        });
      }
      return res.data;
    }
    return res;
  },

  async (error: AxiosError | any) => {
    const originalRequest = error.config as AxiosRequestConfig & { _retry?: boolean };

    const responseData = error.response?.data;
    console.error("API error response:", responseData);
    // if (isCustomApiResponse(responseData)) {
    //   toast.error(responseData.message || "An error occurred");
    // } else {
    //   toast.error(error.message || "An unexpected error occurred");
    // }

    if (error.isTokenExpired && !originalRequest._retry) {
      if (isRefreshing) {
        try {
          const newToken = await new Promise<string | null>((resolve, reject) => {
            failedQueue.push({ resolve, reject });
          });
          if (newToken && originalRequest.headers) {
            originalRequest.headers["Authorization"] = `Bearer ${newToken}`;
          }
          return axiosInstance(originalRequest);
        } catch (err) {
          return Promise.reject(err);
        }
      }

      originalRequest._retry = true;
      isRefreshing = true;

      try {
        const refreshResp = await axios.post<CustomApiResponse<TokenResponse>>(
          `${API_BASE_URL}/auth/refresh`,
          {},
          {
            withCredentials: true,
            headers: { "X-Device-Id": ensureDeviceId() },
          }
        );

        const newAccessToken = refreshResp.data.data.accessToken;
        localStorage.setItem("accessToken", newAccessToken);

        processQueue(null, newAccessToken);
        isRefreshing = false;

        if (originalRequest.headers) {
          originalRequest.headers["Authorization"] = `Bearer ${newAccessToken}`;
        }

        return axiosInstance(originalRequest);
      } catch (err) {
        processQueue(err, null);
        isRefreshing = false;

        localStorage.removeItem("accessToken");
        window.location.href = "/login";
        return Promise.reject(err);
      }
    }
    return Promise.reject(error);
  }
);

export default axiosInstance;
