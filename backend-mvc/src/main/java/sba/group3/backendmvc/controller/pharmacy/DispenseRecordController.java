package sba.group3.backendmvc.controller.pharmacy;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.*;
import sba.group3.backendmvc.dto.request.pharmacy.DispenseRecordRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.pharmacy.DispenseRecordResponse;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.pharmacy.DispenseRecord;
import sba.group3.backendmvc.entity.pharmacy.DispenseStatus;
import sba.group3.backendmvc.service.pharmacy.DispenseRecordService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/dispense-records")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Dispense Record Management", description = "APIs for managing dispense records")
public class DispenseRecordController {

    private final DispenseRecordService dispenseRecordService;

    @GetMapping("/staff/today")
    public ResponseEntity<CustomApiResponse<List<DispenseRecordResponse>>> getOrdersForStaffToday(
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID staffId = UUID.fromString(jwt.getClaimAsString("staffId"));
        log.info("Fetching today's service orders for staff with id: {}", staffId);
        Instant now = Instant.now();
        ZoneId zone = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDate today = now.atZone(zone).toLocalDate();
        SearchFilter filter = SearchFilter.builder()
                .filterGroup(FilterGroup.builder()
                        .operator(LogicalOperator.AND)
                        .filters(
                                List.of(
                                        Filter.builder()
                                                .field(DispenseRecord.Fields.dispensedBy + ".id")
                                                .operator("eq")
                                                .value(staffId)
                                                .build(),
                                        Filter.builder()
                                                .field(BaseEntity.Fields.createdDate)
                                                .operator("between")
                                                .value(List.of(
                                                        today.atStartOfDay(zone).toInstant(),
                                                        today.plusDays(1).atStartOfDay(zone).toInstant()
                                                ))
                                                .build()
                                        ,
                                        Filter.builder()
                                                .field(DispenseRecord.Fields.status)
                                                .operator("eq")
                                                .value(
                                                        DispenseStatus.PENDING
                                                )
                                                .build()
                                )
                        )
                        .build())
                .sorts(List.of(
                        SortRequest.builder()
                                .field(BaseEntity.Fields.createdDate)
                                .direction(Sort.Direction.ASC)
                                .build()
                ))
                .build();
        List<DispenseRecordResponse> responseList = dispenseRecordService.filterList(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<List<DispenseRecordResponse>>builder()
                        .data(responseList)
                        .build()
        );
    }


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
