package sba.group3.backendmvc.dto.response.laboratory;

import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.entity.laboratory.LabStatus;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.laboratory.LabTestResult}
 */
public record LabTestResultResponse(UUID id, LabTestResponse labTest, LabStatus status, String resultValue, String unit,
                                    String referenceRange, String remark, StaffResponse verifiedBy,
                                    LocalDateTime verifiedAt) implements Serializable {
}