package sba.group3.backendmvc.controller.examination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderResponse;
import sba.group3.backendmvc.service.examination.ServiceOrderService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/examinations")
public class ServiceOrderController {
    private final ServiceOrderService serviceOrderService;

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
}
