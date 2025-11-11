package sba.group3.backendmvc.controller.appointment;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.*;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.appointment.QueueTicketResponse;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.appointment.QueueStatus;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.service.appointment.QueueTicketService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/queue-tickets")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Queue Ticket Management", description = "APIs for managing queue tickets")
public class QueueTicketController {

    private final QueueTicketService queueTicketService;

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @GetMapping("/doctor/today")
    public ResponseEntity<CustomApiResponse<List<QueueTicketResponse>>> getQueueForDoctorToday(
            @AuthenticationPrincipal Jwt jwt
            ) {
        var staffId = UUID.fromString(jwt.getClaimAsString("staffId"));
        log.info("Fetching today's service orders for staff with id: {}", staffId);
        Instant now = Instant.now();
        ZoneId zone = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDate today = now.atZone(zone).toLocalDate();
        SearchFilter filter = SearchFilter.builder()
                .filterGroup(FilterGroup.builder()
                        .operator(LogicalOperator.AND)
                        .filters(
                                List.of(
                                        Filter.builder()
                                                .field(QueueTicket.Fields.assignedDoctor + ".id")
                                                .operator("eq")
                                                .value(staffId)
                                                .build(),
                                        Filter.builder()
                                                .field(BaseEntity.Fields.createdDate)
                                                .operator("between")
                                                .value(List.of(
                                                        today.atStartOfDay(zone).toInstant(),
                                                        today.plusDays(1).atStartOfDay(zone).toInstant()
                                                ))
                                                .build()
                                        ,
                                        Filter.builder()
                                                .field(QueueTicket.Fields.status)
                                                .operator("notIn")
                                                .value(
                                                        List.of(
                                                                QueueStatus.CANCELLED,
                                                                QueueStatus.DONE
                                                        )
                                                )
                                                .build()
                                )
                        )
                        .build())
                .sorts(List.of(
                        SortRequest.builder()
                                .field(BaseEntity.Fields.createdDate)
                                .direction(Sort.Direction.ASC)
                                .build()
                ))
                .build();
        List<QueueTicketResponse> responseList = queueTicketService.filterList(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<List<QueueTicketResponse>>builder()
                        .data(responseList)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PostMapping("/{queueTicketId}/call")
    public ResponseEntity<CustomApiResponse<QueueTicketResponse>> callQueueTicket(
            @PathVariable UUID queueTicketId
    ) {
        log.info("Calling queue ticket with id: {}", queueTicketId);
        return ResponseEntity.ok(
                CustomApiResponse.<QueueTicketResponse>builder()
                        .data(queueTicketService.call(queueTicketId))
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PostMapping("/{queueTicketId}/start")
    public ResponseEntity<CustomApiResponse<QueueTicketResponse>> startQueueTicket(
            @PathVariable UUID queueTicketId
    ) {
        log.info("Starting queue ticket with id: {}", queueTicketId);
        return ResponseEntity.ok(
                CustomApiResponse.<QueueTicketResponse>builder()
                        .data(queueTicketService.start(queueTicketId))
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PostMapping("/{queueTicketId}/skip")
    public ResponseEntity<CustomApiResponse<QueueTicketResponse>> skipQueueTicket(
            @PathVariable UUID queueTicketId
    ) {
        log.info("Skipping queue ticket with id: {}", queueTicketId);
        return ResponseEntity.ok(
                CustomApiResponse.<QueueTicketResponse>builder()
                        .data(queueTicketService.skip(queueTicketId))
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PostMapping("/{queueTicketId}/requeue")
    public ResponseEntity<CustomApiResponse<QueueTicketResponse>> requeueQueueTicket(
            @PathVariable UUID queueTicketId
    ) {
        log.info("Re-queuing ticket with id: {}", queueTicketId);
        return ResponseEntity.ok(
                CustomApiResponse.<QueueTicketResponse>builder()
                        .data(queueTicketService.requeue(queueTicketId))
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PostMapping("/{queueTicketId}/wait-result")
    public ResponseEntity<CustomApiResponse<QueueTicketResponse>> waitResultQueueTicket(
            @PathVariable UUID queueTicketId
    ) {
        log.info("Setting queue ticket to wait result with id: {}", queueTicketId);
        return ResponseEntity.ok(
                CustomApiResponse.<QueueTicketResponse>builder()
                        .data(queueTicketService.waitResult(queueTicketId))
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PostMapping("/{queueTicketId}/resume")
    public ResponseEntity<CustomApiResponse<QueueTicketResponse>> resumeQueueTicket(
            @PathVariable UUID queueTicketId
    ) {
        log.info("Resuming queue ticket with id: {}", queueTicketId);
        return ResponseEntity.ok(
                CustomApiResponse.<QueueTicketResponse>builder()
                        .data(queueTicketService.resume(queueTicketId))
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PostMapping("/{queueTicketId}/done")
    public ResponseEntity<CustomApiResponse<QueueTicketResponse>> doneQueueTicket(
            @PathVariable UUID queueTicketId
    ) {
        log.info("Marking queue ticket as done with id: {}", queueTicketId);
        return ResponseEntity.ok(
                CustomApiResponse.<QueueTicketResponse>builder()
                        .data(queueTicketService.done(queueTicketId))
                        .build()
        );
    }

}
