package sba.group3.backendmvc.controller.appointment;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.appointment.QueueTicketResponse;
import sba.group3.backendmvc.service.appointment.QueueTicketService;

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

    @GetMapping("/doctor/today")
    public ResponseEntity<CustomApiResponse<List<QueueTicketResponse>>> getQueueForDoctorToday(
            @AuthenticationPrincipal Jwt jwt
            ) {
        UUID doctorId = UUID.fromString(jwt.getClaimAsString("staffId"));
        log.info("Fetching today's queue for doctor");
        return ResponseEntity.ok(
                CustomApiResponse.<List<QueueTicketResponse>>builder()
                        .data(queueTicketService.getDoctorQueueToday(doctorId))
                        .build()
        );
    }

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
