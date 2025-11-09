package sba.group3.backendmvc.controller.report;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.service.report.ReportService;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin/report")
@RequiredArgsConstructor
@Tag(name = "Report API")
public class ReportController {

    private final ReportService reportService;


    @GetMapping("/today")
    public ResponseEntity<CustomApiResponse<TodaySummaryResponse>> getTodaySummary() {
        return ResponseEntity.ok(
                CustomApiResponse.<TodaySummaryResponse>builder()
                        .data(reportService.getTodaySummary())
                        .build()
        );
    }

    public record TodaySummaryResponse(
            long totalPatients,
            long completedExams,
            long waitingPatients,
            long inService,
            long invoices,
            BigDecimal totalRevenue,
            long labOrders,
            long imagingOrders,
            long avgWaitingMinutes
    ) {}

    @GetMapping("/patient-by-hour")
    public ResponseEntity<CustomApiResponse<List<PatientByHourResponse>>> getPatientByHour() {
        return ResponseEntity.ok(
                CustomApiResponse.<List<PatientByHourResponse>>builder()
                        .data(reportService.getPatientByHour())
                        .build()
        );
    }

    public record PatientByHourResponse(
            int hour,
            long count
    ) {}

    @GetMapping("/patient-by-day")
    public ResponseEntity<CustomApiResponse<List<PatientByDayResponse>>> getPatientByDay(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<List<PatientByDayResponse>>builder()
                        .data(reportService.getPatientByDay(from, to))
                        .build()
        );
    }

    public record PatientByDayResponse(
            int year,
            int month,
            int day,
            long count
    ) {}

    @GetMapping("/revenue-daily")
    public ResponseEntity<CustomApiResponse<List<RevenueDailyResponse>>> getRevenueDaily(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<List<RevenueDailyResponse>>builder()
                        .data(reportService.getRevenueDaily(from, to))
                        .build()
        );
    }

    public record RevenueDailyResponse(
            int year,
            int month,
            int day,
            BigDecimal revenue
    ) {}

    @GetMapping("/queue-status-today")
    public ResponseEntity<CustomApiResponse<Map<String, Long>>> getQueueStatusToday() {
        return ResponseEntity.ok(
                CustomApiResponse.<Map<String, Long>>builder()
                        .data(reportService.getQueueStatusToday())
                        .build()
        );
    }

    @GetMapping("/patient-specialty")
    public ResponseEntity<CustomApiResponse<List<SpecialtyDistributionResponse>>> getSpecialtyDistribution() {
        return ResponseEntity.ok(
                CustomApiResponse.<List<SpecialtyDistributionResponse>>builder()
                        .data(reportService.getSpecialtyDistribution())
                        .build()
        );
    }

    public record SpecialtyDistributionResponse(
            String specialty,
            long count
    ) {}

    @GetMapping("/staff-workload")
    public ResponseEntity<CustomApiResponse<List<StaffWorkloadResponse>>> getStaffWorkload(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<List<StaffWorkloadResponse>>builder()
                        .data(reportService.getStaffWorkload(from, to))
                        .build()
        );
    }

    public record StaffWorkloadResponse(
            String id,
            String fullName,
            long workload
    ) {}

    @GetMapping("/service-usage")
    public ResponseEntity<CustomApiResponse<List<ServiceUsageResponse>>> getServiceUsage(
            @RequestParam LocalDate from,
            @RequestParam LocalDate to
    ) {
        return ResponseEntity.ok(
                CustomApiResponse.<List<ServiceUsageResponse>>builder()
                        .data(reportService.getServiceUsage(from, to))
                        .build()
        );
    }

    public record ServiceUsageResponse(
            String serviceName,
            String category,
            long usedCount,
            BigDecimal totalPrice
    ) {}
}
