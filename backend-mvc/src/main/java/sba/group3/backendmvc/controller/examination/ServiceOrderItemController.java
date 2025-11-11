package sba.group3.backendmvc.controller.examination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderItemRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.service.examination.ServiceOrderItemService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/service-order-items")
public class ServiceOrderItemController {
    private final ServiceOrderItemService serviceOrderItemService;


    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<?>> updateServiceOrderItem(
            @PathVariable String id,
            @RequestBody ServiceOrderItemRequest request
            ) {
        log.info("Updating service order item with id: {} and request: {}", id, request);
        var response = serviceOrderItemService.update(id, request);
        return ResponseEntity.ok(
                CustomApiResponse.builder()
                        .data(response)
                        .message("Service order item updated successfully")
                        .build()
        );
    }


}
