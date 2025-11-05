package sba.group3.backendmvc.dto.response.examination;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.ServiceOrderItem}
 */
public record ServiceOrderItemResponse(UUID id, ServiceCatalogResponse service, BigDecimal price,
                                       String note) implements Serializable {
}