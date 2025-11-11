package sba.group3.backendmvc.controller.staff;


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
import sba.group3.backendmvc.dto.request.staff.SpecialtyRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.staff.SpecialtyResponse;
import sba.group3.backendmvc.service.staff.SpecialtyService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/admin/specialties")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
@Tag(name = "Specialty Management", description = "APIs for managing specialty by admin")
public class SpecialtyController {


    private final SpecialtyService specialtyService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<SpecialtyResponse>>> filter(
            @RequestBody SearchFilter filter) {
        log.info("Filtering users by admin {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<SpecialtyResponse>>builder()
                        .data(specialtyService.filter(filter))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<SpecialtyResponse>> getById(
            @PathVariable UUID id
    ) {
        log.info("Getting specialty by id {}", id);
        SpecialtyResponse response = specialtyService.getById(id);
        return ResponseEntity.ok(
                CustomApiResponse.<SpecialtyResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<SpecialtyResponse>> create(
            @RequestBody @Validated SpecialtyRequest request
    ){
        log.info("Creating position {}", request);
        var response = specialtyService.create(request);
        return ResponseEntity.ok(
                CustomApiResponse.<SpecialtyResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<SpecialtyResponse>> update(
            @PathVariable UUID id,
            @RequestBody @Validated SpecialtyRequest request
    ){
        log.info("Updating position with id {}: {}", id, request);
        SpecialtyResponse response = specialtyService.update(id, request);
        return ResponseEntity.ok(
                CustomApiResponse.<SpecialtyResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(
            @PathVariable UUID id
    ){
        log.info("Deleting position with id {}", id);
        specialtyService.delete(id);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Position deleted successfully")
                        .build()
        );
    }



}
