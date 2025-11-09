import { apiClient } from "../client";

export const ReportApi = {
  getTodaySummary: () =>
    apiClient.get("/admin/report/today"),

  getPatientByHour: () =>
    apiClient.get("/admin/report/patient-by-hour"),

  getQueueStatusToday: () =>
    apiClient.get("/admin/report/queue-status-today"),

  getSpecialtyDistribution: () =>
    apiClient.get("/admin/report/patient-specialty"),

  getPatientByDay: (from: string, to: string) =>
    apiClient.get("/admin/report/patient-by-day", { params: { from, to } }),

  getRevenueDaily: (from: string, to: string) =>
    apiClient.get("/admin/report/revenue-daily", { params: { from, to } }),

  getStaffWorkload: (from: string, to: string) =>
    apiClient.get("/admin/report/staff-workload", { params: { from, to } }),

  getServiceUsage: (from: string, to: string) =>
    apiClient.get("/admin/report/service-usage", { params: { from, to } }),
};
