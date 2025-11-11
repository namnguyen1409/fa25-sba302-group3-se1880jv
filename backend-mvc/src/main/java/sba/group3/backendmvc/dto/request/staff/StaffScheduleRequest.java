package sba.group3.backendmvc.dto.request.staff;

import sba.group3.backendmvc.entity.staff.ScheduleStatus;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.StaffSchedule}
 */
public record StaffScheduleRequest(UUID staffId, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime,
                                   boolean available, UUID roomId, LocalDate date, ScheduleStatus status, String note) implements Serializable {
}