package sba.group3.backendmvc.dto.response.staff;

import sba.group3.backendmvc.dto.response.organization.RoomResponse;
import sba.group3.backendmvc.entity.staff.ScheduleStatus;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.staff.StaffSchedule}
 */
public record StaffScheduleResponse(UUID id, DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime,
                                    boolean available, StaffResponse staff, RoomResponse room, LocalDate date,
                                    ScheduleStatus status, String note) implements Serializable {
}