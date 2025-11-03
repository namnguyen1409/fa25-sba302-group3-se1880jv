package sba.group3.backendmvc.dto.request.staff;

import sba.group3.backendmvc.entity.staff.StaffType;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.StaffScheduleTemplate}
 */
public record StaffScheduleTemplateRequest(UUID staffId, StaffType staffStaffType, String staffLicenseNumber,
                                           Integer staffExperienceYears, String staffEducation, String staffBio,
                                           LocalDate staffJoinedDate, DayOfWeek dayOfWeek, LocalTime startTime,
                                           LocalTime endTime, boolean active) implements Serializable {
}