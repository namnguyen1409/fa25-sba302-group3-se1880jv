package sba.group3.backendmvc.controller.examination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.service.examination.ServiceOrderItemService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/examinations")
public class ServiceOrderItemController {
    private final ServiceOrderItemService serviceOrderItemService;
    private final RestClient.Builder builder;

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
