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
import sba.group3.backendmvc.dto.request.pharmacy.MedicineRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.pharmacy.MedicineResponse;
import sba.group3.backendmvc.service.pharmacy.MedicineService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/medicines")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Medicine Management", description = "APIs for managing medicines")
public class MedicineController {


    private final MedicineService medicineService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<MedicineResponse>>> filter(
            @RequestBody SearchFilter filter) {
        log.info("Filtering medicines by admin {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<MedicineResponse>>builder()
                        .data(medicineService.filter(filter))
                        .build()
        );
    }

    @PostMapping
    public  ResponseEntity<CustomApiResponse<MedicineResponse>> create(
            @RequestBody MedicineRequest medicineRequest
    ) {
        log.info("Creating medicines by admin {}", medicineRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<MedicineResponse>builder()
                        .data(medicineService.create(medicineRequest))
                        .build()
        );
    }

    @GetMapping("/{medicineId}")
    public  ResponseEntity<CustomApiResponse<MedicineResponse>> getById(
            @PathVariable UUID medicineId
    ) {
        log.info("Getting medicine by id {}", medicineId);
        return ResponseEntity.ok(
                CustomApiResponse.<MedicineResponse>builder()
                        .data(medicineService.getById(medicineId))
                        .build()
        );
    }


    @PutMapping("/{medicineId}")
    public  ResponseEntity<CustomApiResponse<MedicineResponse>> update(
            @PathVariable UUID medicineId,
            @RequestBody MedicineRequest medicineRequest
    ) {
        log.info("Updating medicines by admin {}", medicineRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<MedicineResponse>builder()
                        .data(medicineService.update(medicineId, medicineRequest))
                        .build()
        );
    }

    @DeleteMapping("/{medicineId}")
    public  ResponseEntity<CustomApiResponse<Void>> delete(
            @PathVariable UUID medicineId
    ) {
        log.info("Deleting medicines by admin {}", medicineId);
        medicineService.delete(medicineId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Medicine deleted successfully")
                        .build()
        );
    }


}
