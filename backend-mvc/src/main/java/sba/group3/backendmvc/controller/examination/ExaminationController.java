package sba.group3.backendmvc.controller.examination;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ExaminationRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.ExaminationResponse;
import sba.group3.backendmvc.service.examination.ExaminationService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/examinations")
public class ExaminationController {
    private final ExaminationService examinationService;
    private final RestClient.Builder builder;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<ExaminationResponse>>> getExaminations(@RequestBody SearchFilter filter) {
        log.info("getExaminations called with filter: {}", filter);
        Page<ExaminationResponse> responseList = examinationService.filter(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<ExaminationResponse>>builder()
                        .data(responseList)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<ExaminationResponse>> createExamination(
            @RequestBody @Validated ExaminationRequest request
    ) {
        log.info("Creating new examination (check-in) with request: {}", request);
        ExaminationResponse response = examinationService.create(request);
        return ResponseEntity.ok(
                CustomApiResponse.<ExaminationResponse>builder()
                        .data(response)
                        .message("Examination created successfully")
                        .build()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ExaminationResponse>> getExaminationDetail(
            @PathVariable String id
    ) {
        log.info("Getting detail for examination with id: {}", id);
        ExaminationResponse response = examinationService.getExaminationDetail(id);
        return ResponseEntity.ok(
                CustomApiResponse.<ExaminationResponse>builder()
                        .data(response)
                        .build()
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ExaminationResponse>> updateExamination(
            @PathVariable String id,
            @RequestBody @Validated ExaminationRequest request
    ) {
        log.info("Updating examination with id {}: {}", id, request);
        ExaminationResponse response = examinationService.update(id, request);
        return ResponseEntity.ok(
                CustomApiResponse.<ExaminationResponse>builder()
                        .data(response)
                        .message("Examination updated successfully")
                        .build()
        );
    }
}
