package sba.group3.backendmvc.controller.organization;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.Filter;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.organization.ClinicRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.organization.ClinicResponse;
import sba.group3.backendmvc.dto.response.organization.DepartmentResponse;
import sba.group3.backendmvc.service.organization.ClinicService;
import sba.group3.backendmvc.service.organization.DepartmentService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/organization/clinics")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Clinic Management", description = "APIs for managing clinics")
public class ClinicController {
    ClinicService clinicService;
    DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<CustomApiResponse<ClinicResponse>> getDefaultClinic() {
        log.info("Fetching default clinic");
        return ResponseEntity.ok(
                CustomApiResponse.<ClinicResponse>builder()
                        .data(clinicService.getDefaultClinic())
                        .message("Default clinic fetched successfully")
                        .build()
        );
    }

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<ClinicResponse>>> filter(
            @RequestBody SearchFilter filter) {
        return ResponseEntity.ok(
                CustomApiResponse.<Page<ClinicResponse>>builder()
                        .data(clinicService.filter(filter))
                        .build()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ClinicResponse>> getClinicById(
            @PathVariable String id
    ) {
        log.info("Fetching clinic with ID: {}", id);
        return ResponseEntity.ok(
                CustomApiResponse.<ClinicResponse>builder()
                        .data(clinicService.getClinicById(id))
                        .message("Clinic fetched successfully")
                        .build()
        );
    }

    @PostMapping("/{clinicId}/departments/filter")
    public ResponseEntity<CustomApiResponse<Page<DepartmentResponse>>> filterDepartmentsByClinic(
            @PathVariable String clinicId,
            @RequestBody SearchFilter filter) {
        log.info("Filtering departments for clinic ID {}: {}", clinicId, filter);
        filter.addMandatoryCondition(
                new Filter("clinic.id", null, "eq", clinicId)
        );
        return ResponseEntity.ok(
                CustomApiResponse.<Page<DepartmentResponse>>builder()
                        .data(departmentService.filterDepartmentsByClinic(filter))
                        .build()
        );
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<ClinicResponse>> create(@RequestBody @Validated ClinicRequest clinicRequest) {
        log.info("Creating clinic {}", clinicRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<ClinicResponse>builder()
                        .data(clinicService.createClinic(clinicRequest))
                        .message("Clinic create successful")
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ClinicResponse>> update(
            @PathVariable UUID id,
            @RequestBody @Validated ClinicRequest clinicRequest) {
        log.info("Updating clinic with ID {}: {}", id, clinicRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<ClinicResponse>builder()
                        .data(clinicService.updateClinic(id, clinicRequest))
                        .message("Clinic update successful")
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable String id) {
        log.info("Deleting clinic with ID: {}", id);
        clinicService.deleteClinic(id);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Clinic deleted successfully")
                        .build()
        );
    }
}
