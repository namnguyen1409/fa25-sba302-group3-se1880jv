package sba.group3.backendmvc.controller.examination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.PrescriptionRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.PrescriptionItemRequest;
import sba.group3.backendmvc.dto.response.examination.PrescriptionItemResponse;
import sba.group3.backendmvc.dto.response.examination.PrescriptionResponse;
import sba.group3.backendmvc.service.examination.PrescriptionItemService;
import sba.group3.backendmvc.service.examination.PrescriptionService;
import sba.group3.backendmvc.service.examination.impl.PrescriptionItemServiceImpl;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/prescriptions")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final PrescriptionItemService prescriptionItemService;

    @PostMapping("/{id}/items/filter")
    public ResponseEntity<CustomApiResponse<Page<PrescriptionItemResponse>>> createPrescriptionForExamination(
            @PathVariable("id") UUID prescriptionId,
            @RequestBody SearchFilter filter
    ) {
        log.info("Filtering prescription items: {}", filter);
        filter.addMandatoryCondition(
                sba.group3.backendmvc.dto.filter.Filter.builder()
                        .field("prescription.id")
                        .operator("eq")
                        .value(prescriptionId)
                        .build()
        );
        Page<PrescriptionItemResponse> responseList = prescriptionItemService.filter(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<PrescriptionItemResponse>>builder()
                        .data(responseList)
                        .message("Filter prescription items successfully")
                        .build()
        );
    }


    @PostMapping("/{id}/items")
    public ResponseEntity<CustomApiResponse<PrescriptionItemResponse>> createPrescriptionItem(
            @PathVariable("id") String prescriptionId,
            @RequestBody PrescriptionItemRequest request
    ) {
        log.info("Creating prescription item for prescription ID: {}", prescriptionId);
        PrescriptionItemResponse response = prescriptionItemService.createItem(prescriptionId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<PrescriptionItemResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/items/{itemId}")
    public ResponseEntity<CustomApiResponse<PrescriptionItemResponse>> updatePrescriptionItem(
            @PathVariable("itemId") String itemId,
            @RequestBody @Validated PrescriptionItemRequest request
    ) {
        log.info("Updating prescription item with id {}: {}", itemId, request);
        PrescriptionItemResponse response = prescriptionItemService.update(itemId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<PrescriptionItemResponse>builder()
                        .data(response)
                        .build()
        );
    }

}
