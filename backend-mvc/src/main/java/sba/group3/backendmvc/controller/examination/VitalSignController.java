package sba.group3.backendmvc.controller.examination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import sba.group3.backendmvc.dto.filter.Filter;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.VitalSignRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.VitalSignResponse;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.VitalSign;
import sba.group3.backendmvc.service.examination.VitalSignService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/examinations")
public class VitalSignController {
    private final VitalSignService vitalSignService;


    @PostMapping("/{id}/vitals/filter")
    public ResponseEntity<CustomApiResponse<Page<VitalSignResponse>>> filterVitals(
            @PathVariable("id") UUID examinationId,
            @RequestBody @Validated SearchFilter filter
    ) {
        log.info("Filtering vitals for examination {}: {}", examinationId, filter);
        filter.addMandatoryCondition(
                Filter.builder()
                        .field(VitalSign.Fields.examination + "." + BaseEntity.Fields.id)
                        .operator("eq")
                        .value(examinationId)
                        .build()
        );
        Page<VitalSignResponse> responseList = vitalSignService.filter(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<VitalSignResponse>>builder()
                        .data(responseList)
                        .build()
        );
    }

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
            @PathVariable("id") UUID examinationId,
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
