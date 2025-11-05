package sba.group3.backendmvc.dto.request.billing;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.billing.Invoice}
 */
public record InvoiceRequest(UUID patientId, UUID examinationId, String invoiceNumber, LocalDateTime issueDate,
                             BigDecimal totalAmount, Boolean paid, String note) implements Serializable {
}