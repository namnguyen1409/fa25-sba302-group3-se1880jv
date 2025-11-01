package sba.group3.backendmvc.dto.request.staff;

import java.io.Serializable;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.Position}
 */
public record PositionRequest(String positionCode, String title, String description) implements Serializable {
}