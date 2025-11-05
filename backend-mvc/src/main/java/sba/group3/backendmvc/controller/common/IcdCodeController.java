package sba.group3.backendmvc.controller.common;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.common.IcdCodeRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.common.IcdCodeResponse;
import sba.group3.backendmvc.service.common.IcdCodeService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/common/icd-codes")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "ICD Code Management", description = "APIs for managing ICD codes")
public class IcdCodeController {
    IcdCodeService icdCodeService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<IcdCodeResponse>>> filter(
            @RequestBody SearchFilter filter
    ) {
        log.info("Filter icd codes: {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<IcdCodeResponse>>builder()
                        .data(icdCodeService.filter(filter))
                        .build()
        );

    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<IcdCodeResponse>> getIcdCodeById(@PathVariable UUID id) {
        return ResponseEntity.ok(
                CustomApiResponse.<IcdCodeResponse>builder()
                        .data(icdCodeService.getById(id))
                        .message("ICD code retrieved successfully")
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<IcdCodeResponse>> createIcdCode(@RequestBody IcdCodeRequest icdCode) {
        return ResponseEntity.ok(
                CustomApiResponse.<IcdCodeResponse>builder()
                        .data(icdCodeService.save(icdCode))
                        .message("ICD code created successfully")
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<IcdCodeResponse>> updateIcdCode(@PathVariable UUID id, @RequestBody IcdCodeRequest icdCode) {
        return ResponseEntity.ok(
                CustomApiResponse.<IcdCodeResponse>builder()
                        .data(icdCodeService.update(id, icdCode))
                        .message("ICD code updated successfully")
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> deleteIcdCode(@PathVariable UUID id) {
        icdCodeService.delete(id);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("ICD code deleted successfully")
                        .build()
        );
    }
}
