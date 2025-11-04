package sba.group3.backendmvc.controller.laboratory;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabTestRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResponse;
import sba.group3.backendmvc.service.laboratory.LabTestService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/lab-tests")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Lab Test Management", description = "APIs for managing lab tests")
public class LabTestController {

    private final LabTestService labTestService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<LabTestResponse>>> filter(
            @RequestBody SearchFilter filter
    ) {
        log.info("Filtering lab tests with filter: {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<LabTestResponse>>builder()
                        .data(labTestService.filter(filter))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<LabTestResponse>> create(
            @RequestBody LabTestRequest labTestRequest
    ) {
        log.info("Creating lab test with data: {}", labTestRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<LabTestResponse>builder()
                        .data(labTestService.create(labTestRequest))
                        .build()
        );
    }

    @GetMapping("/{testId}")
    public ResponseEntity<CustomApiResponse<LabTestResponse>> getById(
            @PathVariable UUID testId
    ) {
        log.info("Getting lab test by id {}", testId);
        return ResponseEntity.ok(
                CustomApiResponse.<LabTestResponse>builder()
                        .data(labTestService.getById(testId))
                        .build()
        );
    }

    @PutMapping("/{testId}")
    public ResponseEntity<CustomApiResponse<LabTestResponse>> update(
            @PathVariable UUID testId,
            @RequestBody LabTestRequest labTestRequest
    ) {
        log.info("Updating lab test with id: {} and data: {}", testId, labTestRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<LabTestResponse>builder()
                        .data(labTestService.update(testId, labTestRequest))
                        .build()
        );
    }

    @DeleteMapping("/{testId}")
    public ResponseEntity<CustomApiResponse<Void>> delete(
            @PathVariable UUID testId
    ) {
        log.info("Deleting lab test with id: {}", testId);
        labTestService.delete(testId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .data(null)
                        .build()
        );
    }


}
