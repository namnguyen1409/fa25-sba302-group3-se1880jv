package sba.group3.backendmvc.dto.request.staff;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.StaffSchedule}
 */
public record StaffScheduleRequest(UUID staffId, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime,
                                   boolean available, UUID roomId) implements Serializable {
}