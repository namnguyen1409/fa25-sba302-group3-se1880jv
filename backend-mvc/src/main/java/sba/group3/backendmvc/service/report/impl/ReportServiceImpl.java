package sba.group3.backendmvc.service.report.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.controller.report.ReportController;
import sba.group3.backendmvc.entity.appointment.QueueStatus;
import sba.group3.backendmvc.entity.examination.ExaminationStatus;
import sba.group3.backendmvc.repository.report.ReportRepository;
import sba.group3.backendmvc.service.report.ReportService;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
public class ReportServiceImpl implements ReportService {

    ReportRepository reportRepository;

    private static ZoneId ZONE() { return ZoneId.of("Asia/Ho_Chi_Minh"); }
    private static Instant startOf(LocalDate d) { return d.atStartOfDay(ZONE()).toInstant(); }
    private static Instant endOf(LocalDate d) { return d.plusDays(1).atStartOfDay(ZONE()).toInstant(); }

    @Override
    public ReportController.TodaySummaryResponse getTodaySummary() {
        LocalDate today = LocalDate.now(ZONE());
        Instant start = startOf(today);
        Instant end = endOf(today);

        long totalPatients   = reportRepository.countQueuesBetween(start, end);
        long waitingPatients = reportRepository.countQueuesByStatusBetween(start, end, QueueStatus.WAITING);
        long inService       = reportRepository.countQueuesByStatusBetween(start, end, QueueStatus.IN_SERVICE);
        long completedExams  = reportRepository.countExamsByStatusBetween(start, end, ExaminationStatus.COMPLETED);
        long invoices        = reportRepository.countInvoicesByCreatedBetween(start, end);
        BigDecimal totalRevenue = reportRepository.sumRevenueBetween(start, end);
        long labOrders       = reportRepository.countLabOrdersBetween(start, end);
        long imagingOrders   = reportRepository.countImagingOrdersBetween(start, end);
        long avgWaitingMinutes = 0;

        return new ReportController.TodaySummaryResponse(
                totalPatients,
                completedExams,
                waitingPatients,
                inService,
                invoices,
                totalRevenue == null ? BigDecimal.ZERO : totalRevenue,
                labOrders,
                imagingOrders,
                avgWaitingMinutes
        );
    }

    @Override
    public List<ReportController.PatientByHourResponse> getPatientByHour() {
        LocalDate today = LocalDate.now(ZONE());
        var rows = reportRepository.patientByHour(startOf(today), endOf(today));
        return rows.stream()
                .map(r -> new ReportController.PatientByHourResponse(
                        ((Number) r[0]).intValue(),
                        ((Number) r[1]).longValue()
                ))
                .toList();
    }

    @Override
    public List<ReportController.PatientByDayResponse> getPatientByDay(LocalDate from, LocalDate to) {
        var rows = reportRepository.patientByDay(startOf(from), endOf(to));
        return rows.stream()
                .map(r -> new ReportController.PatientByDayResponse(
                        ((Number) r[0]).intValue(), // year
                        ((Number) r[1]).intValue(), // month
                        ((Number) r[2]).intValue(), // day
                        ((Number) r[3]).longValue()
                ))
                .toList();
    }

    @Override
    public List<ReportController.RevenueDailyResponse> getRevenueDaily(LocalDate from, LocalDate to) {
        var rows = reportRepository.revenueDaily(startOf(from), endOf(to));
        return rows.stream()
                .map(r -> new ReportController.RevenueDailyResponse(
                        ((Number) r[0]).intValue(), // year
                        ((Number) r[1]).intValue(), // month
                        ((Number) r[2]).intValue(), // day
                        (BigDecimal) r[3]
                ))
                .toList();
    }

    @Override
    public Map<String, Long> getQueueStatusToday() {
        LocalDate today = LocalDate.now(ZONE());
        return reportRepository.queueStatusBetween(startOf(today), endOf(today))
                .stream()
                .collect(Collectors.toMap(
                        r -> r[0].toString(),
                        r -> ((Number) r[1]).longValue()
                ));
    }

    @Override
    public List<ReportController.SpecialtyDistributionResponse> getSpecialtyDistribution() {
        return reportRepository.specialtyDistribution().stream()
                .map(r -> new ReportController.SpecialtyDistributionResponse(
                        (String) r[0],
                        ((Number) r[1]).longValue()
                ))
                .toList();
    }

    @Override
    public List<ReportController.StaffWorkloadResponse> getStaffWorkload(LocalDate from, LocalDate to) {
        var rows = reportRepository.staffWorkload(startOf(from), endOf(to));
        return rows.stream()
                .map(r -> new ReportController.StaffWorkloadResponse(
                        r[0].toString(),
                        (String) r[1],
                        ((Number) r[2]).longValue()
                ))
                .toList();
    }

    @Override
    public List<ReportController.ServiceUsageResponse> getServiceUsage(LocalDate from, LocalDate to) {
        var rows = reportRepository.serviceUsage(startOf(from), endOf(to));
        return rows.stream()
                .map(r -> new ReportController.ServiceUsageResponse(
                        (String) r[0],                 // serviceName
                        (String) r[1],                 // category
                        ((Number) r[2]).longValue(),   // usedCount
                        (BigDecimal) r[3]              // totalPrice
                ))
                .toList();
    }
}
