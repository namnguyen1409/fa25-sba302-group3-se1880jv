package sba.group3.backendmvc.dto.request.pharmacy;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link sba.group3.backendmvc.entity.pharmacy.Medicine}
 */
public record MedicineRequest(String code, String name, String activeIngredient, String dosageForm, String strength,
                              BigDecimal price, String unit, String description) implements Serializable {
}