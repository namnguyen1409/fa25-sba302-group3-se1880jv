package sba.group3.backendmvc.dto.request.laboratory;

import sba.group3.backendmvc.entity.laboratory.LabStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.laboratory.LabTestResult}
 */
public record LabTestResultRequest(UUID labOrderId, UUID labTestId, LabStatus status, String resultValue, String unit,
                                   String referenceRange, String remark, UUID verifiedById,
                                   LocalDateTime verifiedAt) implements Serializable {
}