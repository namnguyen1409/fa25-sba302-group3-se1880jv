package sba.group3.backendmvc.dto.response.staff;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.Position}
 */
public record PositionResponse(UUID id, String positionCode, String title, String description) implements Serializable {
}