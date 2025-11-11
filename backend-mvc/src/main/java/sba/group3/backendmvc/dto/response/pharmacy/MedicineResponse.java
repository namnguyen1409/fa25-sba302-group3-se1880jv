package sba.group3.backendmvc.dto.response.pharmacy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.pharmacy.Medicine}
 */
public record MedicineResponse(UUID id, String code, String name, String activeIngredient, String dosageForm,
                               String strength, BigDecimal price, String unit,
                               String description) implements Serializable {
}