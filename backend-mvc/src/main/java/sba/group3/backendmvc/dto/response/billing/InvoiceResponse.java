package sba.group3.backendmvc.dto.response.billing;

import sba.group3.backendmvc.dto.response.examination.ExaminationResponse;
import sba.group3.backendmvc.dto.response.patient.PatientResponse;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.billing.Invoice}
 */
public record InvoiceResponse(UUID id, PatientResponse patient, ExaminationResponse examination, String invoiceNumber,
                              LocalDateTime issueDate, BigDecimal totalAmount, Boolean paid, String note,
                              Set<InvoiceItemResponse> items) implements Serializable {
}