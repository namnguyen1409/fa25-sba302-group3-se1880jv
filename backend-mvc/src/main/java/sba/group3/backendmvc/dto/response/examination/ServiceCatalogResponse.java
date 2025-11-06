package sba.group3.backendmvc.dto.response.examination;

import sba.group3.backendmvc.entity.organization.RoomType;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.examination.ServiceCatalog}
 */
public record ServiceCatalogResponse(UUID id, String code, String name, String category, BigDecimal price,
                                     String description, RoomType roomType) implements Serializable {
}