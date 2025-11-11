package sba.group3.backendmvc.dto.request.examination;

import sba.group3.backendmvc.entity.organization.RoomType;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.ServiceCatalog}
 */
public record ServiceCatalogRequest(String name, String category, BigDecimal price,
                                    String description, RoomType roomType) implements Serializable {
}