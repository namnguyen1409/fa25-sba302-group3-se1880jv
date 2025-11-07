package sba.group3.backendmvc.controller.examination;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import sba.group3.backendmvc.dto.filter.Filter;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ExaminationRequest;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.ExaminationResponse;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderResponse;
import sba.group3.backendmvc.service.examination.ExaminationService;
import sba.group3.backendmvc.service.examination.ServiceOrderItemService;
import sba.group3.backendmvc.service.examination.ServiceOrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/examinations")
public class ExaminationController {
    private final ExaminationService examinationService;
    private final RestClient.Builder builder;
    private final ServiceOrderService serviceOrderService;
    private final ServiceOrderItemService serviceOrderItemService;

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

    @PostMapping("/{examId}/services/filter")
    public ResponseEntity<CustomApiResponse<Page<ServiceOrderResponse>>> filterServiceOrders(
            @PathVariable UUID examId,
            @RequestBody @Validated SearchFilter filter
    ) {
        log.info("Filtering service orders for examination {}: {}", examId, filter);
        filter.addMandatoryCondition(
                Filter.builder()
                        .field("examination.id")
                        .operator("eq")
                        .value(examId.toString())
                        .build()
        );
        Page<ServiceOrderResponse> responsePage = serviceOrderService.filter(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<ServiceOrderResponse>>builder()
                        .data(responsePage)
                        .build()
        );
    }

    @GetMapping("/{id}/services")
    public ResponseEntity<CustomApiResponse<ServiceOrderResponse>> getServiceOrders(
            @PathVariable("id") String examinationId
    ) {
        log.info("Getting service orders for examination with id: {}", examinationId);
        ServiceOrderResponse responseList = serviceOrderService.getServiceOrdersByExaminationId(examinationId);
        return ResponseEntity.ok(
                CustomApiResponse.<ServiceOrderResponse>builder()
                        .data(responseList)
                        .build()
        );
    }


    public record CreateServiceOrderRequest(
            List<UUID> serviceIds
    ) {}

    @PostMapping("/{examId}/services/orders")
    public ResponseEntity<CustomApiResponse<List<ServiceOrderResponse>>> createOrders(
            @PathVariable UUID examId,
            @RequestBody CreateServiceOrderRequest request
    ) {
        log.info("Creating service orders for examination {}: {}", examId, request);
        List<ServiceOrderResponse> responseList = serviceOrderService.createOrders(examId, request.serviceIds());
        return ResponseEntity.ok(
                CustomApiResponse.<List<ServiceOrderResponse>>builder()
                        .data(responseList)
                        .message("Service orders created successfully")
                        .build()
        );
    }

    @PostMapping("/{id}/services")
    public ResponseEntity<CustomApiResponse<ServiceOrderResponse>> createServiceOrder(
            @PathVariable("id") String examinationId,
            @RequestBody @Validated ServiceOrderRequest request
    ) {
        log.info("Creating new service order for examination {}: {}", examinationId, request);
        ServiceOrderResponse response = serviceOrderService.create(request);
        return ResponseEntity.ok(
                CustomApiResponse.<ServiceOrderResponse>builder()
                        .data(response)
                        .message("Service order created successfully")
                        .build()
        );
    }


    @PutMapping("/{id}/services/{serviceOrderId}")
    public ResponseEntity<CustomApiResponse<ServiceOrderResponse>> saveOrUpdateServiceOrders(
            @PathVariable("id") String examinationId,
            @PathVariable("serviceOrderId") String serviceOrderId,
            @RequestBody @Validated ServiceOrderRequest request
    ) {
        log.info("Saving/Updating service orders for examination {}: {}", examinationId, request);
        ServiceOrderResponse response = serviceOrderService.update(serviceOrderId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<ServiceOrderResponse>builder()
                        .data(response)
                        .message("Service orders saved/updated successfully")
                        .build()
        );
    }

    @DeleteMapping("/{id}/services/item/{itemId}")
    public ResponseEntity<CustomApiResponse<Void>> deleteServiceOrderItem(
            @PathVariable("id") String examinationId,
            @PathVariable("itemId") String itemId
    ) {
        log.info("Deleting service order item {} from examination {}", itemId, examinationId);
        serviceOrderItemService.delete(itemId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Service order item deleted successfully")
                        .build()
        );
    }
}
