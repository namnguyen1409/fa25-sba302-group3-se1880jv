package sba.group3.backendmvc.dto.request.organization;

import jakarta.validation.constraints.*;

import java.io.Serializable;

/**
 * DTO for {@link sba.group3.backendmvc.entity.organization.Room}
 */
public record RoomRequest(
        @NotBlank(message = "Name can not be blank")
        @Size(max = 100, message = "Name must not exceed 100 characters")
        String name,

        @Size(max = 50, message = "Room type must not exceed 50 characters")
        String roomType,

        @Min(value = 1, message = "Floor number must be at least 1")
        @Max(value = 200, message = "Floor number must not exceed 200")
        Integer floorNumber,

        @Positive(message = "Capacity must be a positive number")
        @Max(value = 1000, message = "Capacity must not exceed 1000")
        Integer capacity,

        String description) implements Serializable {
}