package sba.group3.backendmvc.dto.request.laboratory;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link sba.group3.backendmvc.entity.laboratory.LabTest}
 */
public record LabTestRequest(String code, String name, String category, BigDecimal price, String unit,
                             String referenceRange, String description) implements Serializable {
}