package sba.group3.backendmvc.dto.request.pharmacy;

import sba.group3.backendmvc.entity.pharmacy.DispenseStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.pharmacy.DispenseRecord}
 */
public record DispenseRecordRequest(UUID prescriptionId, UUID dispensedById, DispenseStatus status,
                                    LocalDateTime dispensedAt, BigDecimal totalCost,
                                    String note) implements Serializable {
}