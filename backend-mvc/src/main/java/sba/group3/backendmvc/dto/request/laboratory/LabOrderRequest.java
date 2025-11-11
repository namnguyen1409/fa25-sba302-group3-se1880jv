package sba.group3.backendmvc.dto.request.laboratory;

import sba.group3.backendmvc.entity.laboratory.LabStatus;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.laboratory.LabOrder}
 */
public record LabOrderRequest(UUID patientId, UUID requestedById, UUID examinationId, LabStatus status,
                              String orderCode) implements Serializable {
}