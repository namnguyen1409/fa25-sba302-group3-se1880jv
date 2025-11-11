package sba.group3.backendmvc.controller.laboratory;

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
import sba.group3.backendmvc.dto.request.laboratory.LabOrderRequest;
import sba.group3.backendmvc.dto.request.laboratory.LabTestResultRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.laboratory.LabOrderResponse;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResultResponse;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.examination.ServiceOrder;
import sba.group3.backendmvc.entity.examination.ServiceOrderStatus;
import sba.group3.backendmvc.entity.laboratory.LabTestResult;
import sba.group3.backendmvc.service.laboratory.LabOrderService;
import sba.group3.backendmvc.service.laboratory.LabTestResultService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/lab-orders")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Lab Order Management", description = "APIs for managing lab orders")
public class LabOrderController {


    private final LabOrderService labOrderService;
    private final LabTestResultService labTestResultService;

    @GetMapping("/staff/today")
    public ResponseEntity<CustomApiResponse<List<LabOrderResponse>>> getLabOrdersForStaffToday(
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
                                                .field(ServiceOrder.Fields.assignedStaff + ".id")
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
                                                .field(ServiceOrder.Fields.status)
                                                .operator("notIn")
                                                .value(
                                                        List.of(
                                                                ServiceOrderStatus.CANCELLED,
                                                                ServiceOrderStatus.COMPLETED
                                                        )
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
        List<LabOrderResponse> responseList = labOrderService.filterList(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<List<LabOrderResponse>>builder()
                        .data(responseList)
                        .build()
        );
    }


    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<LabOrderResponse>>> filter(
            @RequestBody SearchFilter filter
    ) {
        log.info("Filtering lab orders with filter: {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<LabOrderResponse>>builder()
                        .data(labOrderService.filter(filter))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<LabOrderResponse>> create(
            @RequestBody LabOrderRequest labOrderRequest
    ) {
        log.info("Creating lab order with data: {}", labOrderRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<LabOrderResponse>builder()
                        .data(labOrderService.create(labOrderRequest))
                        .build()
        );
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<CustomApiResponse<LabOrderResponse>> getById(
            @PathVariable UUID orderId
    ) {
        log.info("Getting lab order by id {}", orderId);
        return ResponseEntity.ok(
                CustomApiResponse.<LabOrderResponse>builder()
                        .data(labOrderService.getById(orderId))
                        .build()
        );
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<CustomApiResponse<LabOrderResponse>> update(
            @PathVariable UUID orderId,
            @RequestBody LabOrderRequest labOrderRequest
    ) {
        log.info("Updating lab order with id: {} and data: {}", orderId, labOrderRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<LabOrderResponse>builder()
                        .data(labOrderService.update(orderId, labOrderRequest))
                        .build()
        );
    }

    @DeleteMapping("/{orderId}")
    public ResponseEntity<CustomApiResponse<Void>> delete(
            @PathVariable UUID orderId
    ) {
        log.info("Deleting lab order with id: {}", orderId);
        labOrderService.delete(orderId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .data(null)
                        .build()
        );
    }

    @PostMapping("/{orderId}/results/filter")
    public ResponseEntity<CustomApiResponse<Page<LabTestResultResponse>>> filterOrderItems(
            @PathVariable UUID orderId,
            @RequestBody SearchFilter filter
    ) {

        filter.addMandatoryCondition(
                Filter.builder()
                        .field(LabTestResult.Fields.labOrder + ".id")
                        .operator("eq")
                        .value(orderId)
                        .build()
        );

        log.info("Filtering lab test results for order id {} with filter: {}", orderId, filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<LabTestResultResponse>>builder()
                        .data(labTestResultService.filter(filter))
                        .build()
        );
    }

    @PostMapping("/{orderId}/results")
    public ResponseEntity<CustomApiResponse<LabTestResultResponse>> addOrderItem(
            @PathVariable UUID orderId,
            @RequestBody LabTestResultRequest labTestResultRequest
    ) {
        log.info("Adding lab test result to order id {} with data: {}", orderId, labTestResultRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<LabTestResultResponse>builder()
                        .data(labTestResultService.update(orderId, labTestResultRequest))
                        .build()
        );
    }

    @PutMapping("/results/{itemId}")
    public ResponseEntity<CustomApiResponse<LabTestResultResponse>> updateOrderItem(
            @PathVariable UUID itemId,
            @RequestBody LabTestResultRequest labTestResultRequest
    ) {
        log.info("Updating lab test result with id {} with data: {}", itemId, labTestResultRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<LabTestResultResponse>builder()
                        .data(labTestResultService.update(itemId, labTestResultRequest))
                        .build()
        );
    }

    @PostMapping("/result/{itemId}/verify")
    public ResponseEntity<CustomApiResponse<Void>> verifyOrderItem(
            @PathVariable UUID itemId,
            @AuthenticationPrincipal Jwt jwt
    ) {
        var staffId = UUID.fromString(jwt.getClaim("staffId"));
        labTestResultService.verify(itemId, staffId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("verified")
                        .build()
        );

    }

    @DeleteMapping("/{orderId}/order-items/{itemId}")
    public ResponseEntity<CustomApiResponse<Void>> deleteOrderItem(
            @PathVariable UUID orderId,
            @PathVariable UUID itemId
    ) {
        log.info("Deleting lab test result with id {} from order id {}", itemId, orderId);
        labTestResultService.deleteFromOrder(orderId, itemId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .data(null)
                        .build()
        );
    }


}
