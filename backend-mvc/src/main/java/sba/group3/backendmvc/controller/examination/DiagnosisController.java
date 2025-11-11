package sba.group3.backendmvc.controller.examination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.DiagnosisRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.DiagnosisResponse;
import sba.group3.backendmvc.service.examination.DiagnosisService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/examinations")
public class DiagnosisController {
    private final DiagnosisService diagnosisService;

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @PostMapping("/{id}/diagnosis/filter")
    public ResponseEntity<CustomApiResponse<Page<DiagnosisResponse>>> filterDiagnosis(
            @PathVariable("id") UUID examinationId,
            @RequestBody @Validated SearchFilter filter
    ) {
        log.info("Filtering diagnosis for examination {}: {}", examinationId, filter);
        filter.addMandatoryCondition(
                sba.group3.backendmvc.dto.filter.Filter.builder()
                        .field("examination.id")
                        .operator("eq")
                        .value(examinationId)
                        .build()
        );
        Page<DiagnosisResponse> responseList = diagnosisService.filter(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<DiagnosisResponse>>builder()
                        .data(responseList)
                        .build()
        );
    }

    @PreAuthorize("hasAnyRole('DOCTOR', 'NURSE')")
    @GetMapping("/{id}/diagnosis")
    public ResponseEntity<CustomApiResponse<DiagnosisResponse>> getDiagnosisList(
            @PathVariable("id") String examinationId
    ) {
        log.info("Getting diagnosis list for examination with id: {}", examinationId);
        DiagnosisResponse diagnoses = diagnosisService.searchByExaminationId(examinationId);
        return ResponseEntity.ok(
                CustomApiResponse.<DiagnosisResponse>builder()
                        .data(diagnoses)
                        .build()
        );
    }

    @PreAuthorize("hasRole('DOCTOR')")
    @PostMapping("/{id}/diagnosis")
    public ResponseEntity<CustomApiResponse<DiagnosisResponse>> addDiagnosis(
            @PathVariable("id") String id,
            @RequestBody @Validated DiagnosisRequest request
    ) {
        log.info("Adding diagnosis for examination with id {}: {}", id, request);
        DiagnosisResponse response = diagnosisService.create(request);
        return ResponseEntity.ok(
                CustomApiResponse.<DiagnosisResponse>builder()
                        .data(response)
                        .message("Diagnosis added successfully")
                        .build()
        );
    }


    @PreAuthorize("hasRole('DOCTOR')")
    @DeleteMapping("/{id}/diagnosis/{diagnosisId}")
    public ResponseEntity<CustomApiResponse<Void>> deleteDiagnosis(
            @PathVariable("id") String examinationId,
            @PathVariable("diagnosisId") String diagnosisId
    ) {
        log.info("Deleting diagnosis with id {} from examination {}", diagnosisId, examinationId);
        diagnosisService.delete(diagnosisId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Diagnosis deleted successfully")
                        .build()
        );
    }

}
