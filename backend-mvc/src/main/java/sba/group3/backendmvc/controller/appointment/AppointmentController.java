package sba.group3.backendmvc.controller.appointment;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.appointment.AppointmentRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.appointment.AppointmentResponse;
import sba.group3.backendmvc.service.appointment.AppointmentService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Appointment Management", description = "APIs for managing appointments")
public class AppointmentController {


    private final AppointmentService appointmentService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<AppointmentResponse>>> filter(
            @RequestBody SearchFilter filter
    ) {
        log.info("Filtering appointments with filter: {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<AppointmentResponse>>builder()
                         .data(appointmentService.filter(filter))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<AppointmentResponse>> create(
            @RequestBody AppointmentRequest appointmentRequest
    ) {
        log.info("Creating appointment with data: {}", appointmentRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<AppointmentResponse>builder()
                        .data(appointmentService.create(appointmentRequest))
                        .build()
        );
    }

    @PutMapping("/{appointmentId}")
    public ResponseEntity<CustomApiResponse<AppointmentResponse>> update(
            @PathVariable UUID appointmentId,
            @RequestBody AppointmentRequest appointmentRequest
    ) {
        log.info("Updating appointment with id: {} and data: {}", appointmentId, appointmentRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<AppointmentResponse>builder()
                        .data(appointmentService.update(appointmentId, appointmentRequest))
                        .build()
        );
    }


    @GetMapping("/{appointmentId}")
    public ResponseEntity<CustomApiResponse<AppointmentResponse>> getById(
            @PathVariable UUID appointmentId
    ) {
        log.info("Getting appointment by id {}", appointmentId);
        return ResponseEntity.ok(
                CustomApiResponse.<AppointmentResponse>builder()
                        .data(appointmentService.getById(appointmentId))
                        .build()
        );
    }

    @DeleteMapping("/{appointmentId}")
    public ResponseEntity<CustomApiResponse<Void>> delete(
            @PathVariable UUID appointmentId
    ) {
        log.info("Deleting appointment with id {}", appointmentId);
        appointmentService.delete(appointmentId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .build()
        );
    }

}
