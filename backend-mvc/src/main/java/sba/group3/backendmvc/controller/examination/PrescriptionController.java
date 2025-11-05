package sba.group3.backendmvc.controller.examination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import sba.group3.backendmvc.dto.request.examination.PrescriptionRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.PrescriptionResponse;
import sba.group3.backendmvc.service.examination.PrescriptionService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/examinations")
public class PrescriptionController {
    private final PrescriptionService prescriptionService;
    private final RestClient.Builder builder;

    @GetMapping("/{id}/prescription")
    public ResponseEntity<CustomApiResponse<PrescriptionResponse>> getPrescription(
            @PathVariable("id") String examinationId
    ) {
        log.info("Getting prescription for examination with id: {}", examinationId);
        PrescriptionResponse response = prescriptionService.getPrescriptionByExaminationId(examinationId);
        return ResponseEntity.ok(
                CustomApiResponse.<PrescriptionResponse>builder()
                        .data(response)
                        .build()
        );
    }


    @PutMapping("/{id}/prescription/{prescriptionId}")
    public ResponseEntity<CustomApiResponse<PrescriptionResponse>> saveOrUpdatePrescription(
            @PathVariable("id") String examinationId,
            @PathVariable("prescriptionId") String prescriptionId,
            @RequestBody @Validated PrescriptionRequest request
    ) {
        log.info("Saving/Updating prescription for examination with id {}: {}", examinationId, request);
        PrescriptionResponse response = prescriptionService.update(prescriptionId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<PrescriptionResponse>builder()
                        .data(response)
                        .message("Prescription saved/updated successfully")
                        .build()
        );
    }


    @DeleteMapping("/{id}/prescription/{prescriptionId}")
    public ResponseEntity<CustomApiResponse<Void>> deletePrescriptionItem(
            @PathVariable("id") String examinationId,
            @PathVariable("prescriptionId") String prescriptionId
    ) {
        log.info("Deleting prescription item {} from examination {}", prescriptionId, examinationId);
        prescriptionService.delete(examinationId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Prescription item deleted successfully")
                        .build()
        );
    }
}
