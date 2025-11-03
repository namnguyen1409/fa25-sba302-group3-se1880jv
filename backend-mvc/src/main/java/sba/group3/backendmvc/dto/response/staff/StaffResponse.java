package sba.group3.backendmvc.dto.response.staff;

import sba.group3.backendmvc.entity.staff.StaffType;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.Staff}
 */
public record StaffResponse(SpecialtyResponse specialty, StaffType staffType, PositionResponse position,
                            String licenseNumber, Integer experienceYears, String education, String bio,
                            LocalDate joinedDate) implements Serializable {
}