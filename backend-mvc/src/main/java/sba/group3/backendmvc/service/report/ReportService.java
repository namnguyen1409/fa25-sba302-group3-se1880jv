package sba.group3.backendmvc.service.report;

import sba.group3.backendmvc.controller.report.ReportController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface ReportService {
    ReportController.TodaySummaryResponse getTodaySummary();

    List<ReportController.PatientByHourResponse> getPatientByHour();

    List<ReportController.PatientByDayResponse> getPatientByDay(LocalDate from, LocalDate to);

    List<ReportController.RevenueDailyResponse> getRevenueDaily(LocalDate from, LocalDate to);

    Map<String, Long> getQueueStatusToday();

    List<ReportController.SpecialtyDistributionResponse> getSpecialtyDistribution();

    List<ReportController.StaffWorkloadResponse> getStaffWorkload(LocalDate from, LocalDate to);

    List<ReportController.ServiceUsageResponse> getServiceUsage(LocalDate from, LocalDate to);
}
