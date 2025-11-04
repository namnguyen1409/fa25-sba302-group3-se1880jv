package sba.group3.backendmvc.controller.examination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import sba.group3.backendmvc.dto.request.examination.VitalSignRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.VitalSignResponse;
import sba.group3.backendmvc.service.examination.VitalSignService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/examinations")
public class VitalSignController {
    private final VitalSignService vitalSignService;
    private final RestClient.Builder builder;

    @GetMapping("/{id}/vitals")
    public ResponseEntity<CustomApiResponse<VitalSignResponse>> getVitals(
            @PathVariable("id") String examinationId
    ) {
        log.info("Getting vitals for examination with id: {}", examinationId);
        VitalSignResponse response = vitalSignService.getVitalsByExaminationId(examinationId);
        return ResponseEntity.ok(
                CustomApiResponse.<VitalSignResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @PostMapping("/{id}/vitals")
    public ResponseEntity<CustomApiResponse<VitalSignResponse>> createVital(
            @PathVariable("id") String examinationId,
            @RequestBody @Validated VitalSignRequest request
    ) {
        log.info("Creating new vital for examination {}: {}", examinationId, request);
        VitalSignResponse response = vitalSignService.create(request);
        return ResponseEntity.ok(
                CustomApiResponse.<VitalSignResponse>builder()
                        .data(response)
                        .message("Vital created successfully")
                        .build()
        );
    }

    @PutMapping("/{id}/vitals/{vitalId}")
    public ResponseEntity<CustomApiResponse<VitalSignResponse>> saveOrUpdateVitals(
            @PathVariable("id") String examinationId,
            @PathVariable("vitalId") String vitalId,
            @RequestBody @Validated VitalSignRequest request
    ) {
        log.info("Saving/Updating vitals for examination {}: {}", examinationId, request);
        VitalSignResponse response = vitalSignService.update(vitalId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<VitalSignResponse>builder()
                        .data(response)
                        .message("Vitals saved/updated successfully")
                        .build()
        );
    }
}
