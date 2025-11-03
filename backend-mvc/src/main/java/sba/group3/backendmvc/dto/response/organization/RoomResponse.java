package sba.group3.backendmvc.dto.response.organization;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.organization.Room}
 */
public record RoomResponse(UUID id, String name, String roomType, Integer floorNumber, Integer capacity,
                           String description) implements Serializable {
}