package sba.group3.backendmvc.dto.response.billing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.billing.InvoiceItem}
 */
public record InvoiceItemResponse(UUID id, String description, Integer quantity, BigDecimal unitPrice,
                                  BigDecimal totalPrice) implements Serializable {
}