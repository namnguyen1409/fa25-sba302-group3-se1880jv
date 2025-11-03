package sba.group3.backendmvc.controller.staff;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.staff.PositionRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.staff.PositionResponse;
import sba.group3.backendmvc.service.staff.PositionService;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/admin/positions")
@Tag(name = "Position Management", description = "APIs for managing position by admin")
public class PositionController {

    private final PositionService positionService;
    private final RestClient.Builder builder;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<PositionResponse>>> filter(
            @RequestBody SearchFilter filter) {
        log.info("Filtering users by admin {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<PositionResponse>>builder()
                        .data(positionService.filter(filter))
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<PositionResponse>> getById(
            @PathVariable UUID id
    ) {
        log.info("Getting position by id {}", id);
        PositionResponse response = positionService.getById(id);
        return ResponseEntity.ok(
                CustomApiResponse.<PositionResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<PositionResponse>> create(
            @RequestBody @Validated PositionRequest positionRequest
    ){
        log.info("Creating position {}", positionRequest);
        PositionResponse response = positionService.create(positionRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<PositionResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<PositionResponse>> update(
            @PathVariable UUID id,
            @RequestBody @Validated PositionRequest request
    ){
        log.info("Updating position with id {}: {}", id, request);
        PositionResponse response = positionService.update(id, request);
        return ResponseEntity.ok(
                CustomApiResponse.<PositionResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(
            @PathVariable UUID id
    ){
        log.info("Deleting position with id {}", id);
        positionService.delete(id);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Position deleted successfully")
                        .build()
        );
    }
}
