package sba.group3.backendmvc.dto.request.staff;

import sba.group3.backendmvc.entity.staff.StaffType;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.Staff}
 */
public record StaffRequest(
        String phone,
        String fullName,
        UUID departmentId,
        UUID specialtyId,
        StaffType staffType,
        UUID positionId,
        String licenseNumber,
        Integer experienceYears,
        String education,
        String bio,
        LocalDate joinedDate,
        String email
) implements Serializable {
}