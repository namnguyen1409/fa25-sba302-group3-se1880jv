package sba.group3.backendmvc.controller.laboratory;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.Filter;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabOrderRequest;
import sba.group3.backendmvc.dto.request.laboratory.LabTestResultRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.laboratory.LabOrderResponse;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResultResponse;
import sba.group3.backendmvc.service.laboratory.LabOrderService;
import sba.group3.backendmvc.service.laboratory.LabTestResultService;

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
                        .field("orderId")
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
                        .data(labTestResultService.create(orderId, labTestResultRequest))
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
