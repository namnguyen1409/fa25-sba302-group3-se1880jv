import axios from "axios";

export const chatApi = axios.create({
  baseURL: import.meta.env.VITE_CHAT_BACKEND_URL || "http://localhost:9888",
});

chatApi.interceptors.request.use((config) => {
  const token = localStorage.getItem("accessToken");
  if (token) config.headers.Authorization = `Bearer ${token}`;
  return config;
});
