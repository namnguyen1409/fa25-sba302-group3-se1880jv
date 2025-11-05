package sba.group3.backendmvc.dto.response.staff;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.StaffScheduleTemplate}
 */
public record StaffScheduleTemplateResponse(UUID id, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime,
                                            boolean active) implements Serializable {
}