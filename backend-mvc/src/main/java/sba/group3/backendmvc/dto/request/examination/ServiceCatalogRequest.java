package sba.group3.backendmvc.dto.request.examination;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.ServiceCatalog}
 */
public record ServiceCatalogRequest(String code, String name, String category, BigDecimal price,
                                    String description) implements Serializable {
}