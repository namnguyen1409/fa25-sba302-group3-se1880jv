package sba.group3.backendmvc.dto.response.pharmacy;

import sba.group3.backendmvc.dto.response.examination.PrescriptionResponse;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.entity.pharmacy.DispenseStatus;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.pharmacy.DispenseRecord}
 */
public record DispenseRecordResponse(UUID id, PrescriptionResponse prescription, StaffResponse dispensedBy,
                                     DispenseStatus status, LocalDateTime dispensedAt, BigDecimal totalCost,
                                     String note) implements Serializable {
}