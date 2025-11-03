package sba.group3.backendmvc.dto.response.laboratory;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.laboratory.LabTest}
 */
public record LabTestResponse(UUID id, String code, String name, String category, BigDecimal price, String unit,
                              String referenceRange, String description) implements Serializable {
}