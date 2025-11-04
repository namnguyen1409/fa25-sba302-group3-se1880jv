package sba.group3.backendmvc.controller.pharmacy;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.pharmacy.DispenseRecordRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.pharmacy.DispenseRecordResponse;
import sba.group3.backendmvc.service.pharmacy.DispenseRecordService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/dispense-records")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Dispense Record Management", description = "APIs for managing dispense records")
public class DispenseRecordController {

    private final DispenseRecordService dispenseRecordService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<DispenseRecordResponse>>> filter(
            @RequestBody SearchFilter filter) {
        log.info("Filtering dispense records by admin {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<DispenseRecordResponse>>builder()
                        .data(dispenseRecordService.filter(filter))
                        .build()
        );
    }

    @GetMapping("/{recordId}")
    public ResponseEntity<CustomApiResponse<DispenseRecordResponse>> getById(
            @PathVariable String recordId
    ) {
        log.info("Getting dispense record by id {}", recordId);
        return ResponseEntity.ok(
                CustomApiResponse.<DispenseRecordResponse>builder()
                        .data(dispenseRecordService.getById(recordId))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<DispenseRecordResponse>> create(
            @RequestBody DispenseRecordRequest dispenseRecordRequest
    ) {
        log.info("Creating dispense record by admin {}", dispenseRecordRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<DispenseRecordResponse>builder()
                        .data(dispenseRecordService.create(dispenseRecordRequest))
                        .build()
        );
    }

    @PutMapping("/{recordId}")
    public ResponseEntity<CustomApiResponse<DispenseRecordResponse>> update(
            @PathVariable UUID recordId,
            @RequestBody DispenseRecordRequest dispenseRecordRequest
    ) {
        log.info("Updating dispense record by id {} with data {}", recordId, dispenseRecordRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<DispenseRecordResponse>builder()
                        .data(dispenseRecordService.update(recordId, dispenseRecordRequest))
                        .build()
        );
    }

    @DeleteMapping("/{recordId}")
    public ResponseEntity<CustomApiResponse<Void>> delete(
            @PathVariable UUID recordId
    ) {
        log.info("Deleting dispense record by id {}", recordId);
        dispenseRecordService.delete(recordId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .build()
        );
    }
}
