package sba.group3.backendmvc.dto.response.examination;

import java.io.Serializable;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.ServiceOrder}
 */
public record ServiceOrderResponse(UUID id, String orderCode,
                                   Set<ServiceOrderItemResponse> items) implements Serializable {
}