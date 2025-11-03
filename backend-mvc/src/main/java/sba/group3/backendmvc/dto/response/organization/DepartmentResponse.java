package sba.group3.backendmvc.dto.response.organization;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.organization.Department}
 */
public record DepartmentResponse(
        UUID id,
        String name,
        String description,
        ClinicSimpleResponse clinic,
        List<RoomResponse> rooms
) implements Serializable {
}