package sba.group3.backendmvc.dto.request.examination;

import sba.group3.backendmvc.entity.examination.ServiceOrderItem;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link ServiceOrderItem}
 */
public record ServiceOrderItemRequest(UUID serviceOrderId, UUID serviceId, BigDecimal price,
                                      String note) implements Serializable {
}