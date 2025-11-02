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
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.organization.DepartmentRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.organization.DepartmentResponse;
import sba.group3.backendmvc.service.organization.DepartmentService;

@Slf4j
@RestController
@RequestMapping("/api/organization/departments")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Department Management", description = "APIs for managing departments")
public class DepartmentController {
    DepartmentService departmentService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<DepartmentResponse>>> filter(
            @RequestBody SearchFilter filter) {
        return ResponseEntity.ok(
                CustomApiResponse.<Page<DepartmentResponse>>builder()
                        .data(departmentService.filter(filter))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<DepartmentResponse>> getDepartmentById(
            @PathVariable String id
    ) {
        log.info("Fetching department with ID: {}", id);
        return ResponseEntity.ok(
                CustomApiResponse.<DepartmentResponse>builder()
                        .data(departmentService.getDepartmentById(id))
                        .message("Department fetched successfully")
                        .build()
        );
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<DepartmentResponse>> create(@RequestBody @Validated DepartmentRequest departmentRequest) {
        log.info("Creating department {}", departmentRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<DepartmentResponse>builder()
                        .data(departmentService.createDepartment(departmentRequest))
                        .message("Department create successful")
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<DepartmentResponse>> update(
            @PathVariable String id,
            @RequestBody @Validated DepartmentRequest departmentRequest) {
        log.info("Updating department with ID: {}", id);
        return ResponseEntity.ok(
                CustomApiResponse.<DepartmentResponse>builder()
                        .data(departmentService.updateDepartment(id, departmentRequest))
                        .message("Department update successful")
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(@PathVariable String id) {
        log.info("Deleting department with ID: {}", id);
        departmentService.deleteDepartment(id);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Department delete successful")
                        .build()
        );
    }


}
