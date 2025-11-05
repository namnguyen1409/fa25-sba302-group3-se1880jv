package sba.group3.backendmvc.controller.staff;


import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.StaffRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleResponse;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleTemplateResponse;
import sba.group3.backendmvc.service.staff.StaffScheduleService;
import sba.group3.backendmvc.service.staff.StaffScheduleTemplateService;
import sba.group3.backendmvc.service.staff.StaffService;

import java.util.UUID;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin/staffs")
@Tag(name = "Staff Management", description = "APIs for managing staff by admin")
public class StaffController {

    private final StaffService staffService;
    private final StaffScheduleService staffScheduleService;
    private final StaffScheduleTemplateService staffScheduleTemplateService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<StaffResponse>>> filter(
            @RequestBody SearchFilter filter) {
        log.info("Filtering users by admin {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<StaffResponse>>builder()
                        .data(staffService.filter(filter))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<StaffResponse>> getById(
            @PathVariable UUID id
    ) {
        log.info("Getting staff by id {}", id);
        StaffResponse response = staffService.getById(id);
        return ResponseEntity.ok(
                CustomApiResponse.<StaffResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<StaffResponse>> create(
            @RequestBody @Validated StaffRequest request
    ){
        log.info("Creating position {}", request);
        var response = staffService.create(request);
        return ResponseEntity.ok(
                CustomApiResponse.<StaffResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<StaffResponse>> update(
            @PathVariable UUID id,
            @RequestBody @Validated StaffRequest request
    ){
        log.info("Updating position with id {}: {}", id, request);
        StaffResponse response = staffService.update(id, request);
        return ResponseEntity.ok(
                CustomApiResponse.<StaffResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(
            @PathVariable UUID id
    ){
        log.info("Deleting position with id {}", id);
        staffService.delete(id);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Position deleted successfully")
                        .build()
        );
    }

    @PostMapping("/{staffId}/schedule/filter")
    public ResponseEntity<CustomApiResponse<Page<StaffScheduleResponse>>> getStaffSchedule(
            @PathVariable UUID staffId,
            @RequestBody SearchFilter filter
    ) {
        filter.addMandatoryCondition(
                new sba.group3.backendmvc.dto.filter.Filter("staff.id", null, "eq", staffId)
        );

        log.info("Filtering staff schedule for staffId {} with filter: {}", staffId, filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<StaffScheduleResponse>>builder()
                        .data(staffScheduleService.filter(filter))
                        .message("Staff schedule retrieved successfully")
                        .build()
        );
    }

    @PostMapping("/{staffId}/schedule")
    public ResponseEntity<CustomApiResponse<StaffScheduleResponse>> createStaffSchedule(
            @PathVariable UUID staffId,
            @RequestBody @Validated StaffScheduleResponse request
    ) {
        log.info("Creating staff schedule for staffId {}: {}", staffId, request);
        StaffScheduleResponse response = staffScheduleService.create(staffId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<StaffScheduleResponse>builder()
                        .data(response)
                        .message("Staff schedule created successfully")
                        .build()
        );
    }

    @PutMapping("/schedule/{scheduleId}")
    public ResponseEntity<CustomApiResponse<StaffScheduleResponse>> updateStaffSchedule(
            @PathVariable UUID scheduleId,
            @RequestBody @Validated StaffScheduleResponse request
    ) {
        log.info("Updating staff schedule with id {}: {}", scheduleId, request);
        StaffScheduleResponse response = staffScheduleService.update(scheduleId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<StaffScheduleResponse>builder()
                        .data(response)
                        .message("Staff schedule updated successfully")
                        .build()
        );
    }

    @DeleteMapping("/schedule/{scheduleId}")
    public ResponseEntity<CustomApiResponse<Void>> deleteStaffSchedule(
            @PathVariable UUID scheduleId
    ) {
        log.info("Deleting staff schedule with id {}", scheduleId);
        staffScheduleService.delete(scheduleId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Staff schedule deleted successfully")
                        .build()
        );
    }

    @PostMapping("/{staffId}/schedule-template/filter")
    public ResponseEntity<CustomApiResponse<Page<StaffScheduleTemplateResponse>>> getStaffScheduleTemplate(
            @PathVariable UUID staffId,
            @RequestBody SearchFilter filter
    ) {
        filter.addMandatoryCondition(
                new sba.group3.backendmvc.dto.filter.Filter("staff.id", null, "eq", staffId)
        );
        log.info("Filtering staff schedule template for staffId {} with filter: {}", staffId, filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<StaffScheduleTemplateResponse>>builder()
                        .data(staffScheduleTemplateService.filter(filter))
                        .message("Staff schedule template retrieved successfully")
                        .build()
        );
    }

    @PostMapping("/{staffId}/schedule-template")
    public ResponseEntity<CustomApiResponse<StaffScheduleTemplateResponse>> createStaffScheduleTemplate(
            @PathVariable UUID staffId,
            @RequestBody @Validated StaffScheduleTemplateResponse request
    ) {
        log.info("Creating staff schedule template for staffId {}: {}", staffId, request);
        StaffScheduleTemplateResponse response = staffScheduleTemplateService.create(staffId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<StaffScheduleTemplateResponse>builder()
                        .data(response)
                        .message("Staff schedule template created successfully")
                        .build()
        );
    }

    @PutMapping("/schedule-template/{templateId}")
    public ResponseEntity<CustomApiResponse<StaffScheduleTemplateResponse>> updateStaffScheduleTemplate(
            @PathVariable UUID templateId,
            @RequestBody @Validated StaffScheduleTemplateResponse request
    ) {
        log.info("Updating staff schedule template with id {}: {}", templateId, request);
        StaffScheduleTemplateResponse response = staffScheduleTemplateService.update(templateId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<StaffScheduleTemplateResponse>builder()
                        .data(response)
                        .message("Staff schedule template updated successfully")
                        .build()
        );
    }

    @DeleteMapping("/schedule-template/{templateId}")
    public ResponseEntity<CustomApiResponse<Void>> deleteStaffScheduleTemplate(
            @PathVariable UUID templateId
    ) {
        log.info("Deleting staff schedule template with id {}", templateId);
        staffScheduleTemplateService.delete(templateId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Staff schedule template deleted successfully")
                        .build()
        );
    }

}
