package sba.group3.backendmvc.controller.appointment;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.Filter;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.appointment.AppointmentRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.appointment.AppointmentResponse;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.appointment.Appointment;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.service.appointment.AppointmentService;

import java.util.List;
import java.util.Set;
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
            @RequestBody SearchFilter filter,
            @AuthenticationPrincipal Jwt jwt
            ) {
        log.info("Filtering appointments with filter: {}", filter);
        applyRoleBasedMandatoryFilter(filter, jwt);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<AppointmentResponse>>builder()
                         .data(appointmentService.filter(filter))
                        .build()
        );
    }

    private void applyRoleBasedMandatoryFilter(SearchFilter filter, Jwt jwt) {
        UUID userId = UUID.fromString(jwt.getSubject());
        List<String> roles = jwt.getClaimAsStringList("scope");

        if (roles.contains("ROLE_SYSTEM_ADMIN") || roles.contains("ROLE_MANAGER")) {
            return;
        }

        if (roles.contains("ROLE_PATIENT")) {
            filter.addMandatoryCondition(Filter.builder()
                    .field(Appointment.Fields.patient + ".id")
                    .operator("eq")
                    .value(userId)
                    .build());
            return;
        }

        if (roles.contains("ROLE_RECEPTIONIST")) {
            filter.addMandatoryCondition(Filter.builder()
                    .field(BaseEntity.Fields.createdBy)
                    .operator("eq")
                    .value(userId.toString())
                    .build());
            return;
        }

        if (roles.contains("ROLE_DOCTOR")) {
            filter.addMandatoryCondition(Filter.builder()
                    .field(Appointment.Fields.queueTicket + QueueTicket.Fields.assignedDoctor + ".id")
                    .operator("eq")
                    .value(userId)
                    .build());
        }
    }

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'MANAGER', 'RECEPTIONIST')")
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

    @PreAuthorize("hasAnyRole('SYSTEM_ADMIN', 'MANAGER', 'RECEPTIONIST')")
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
