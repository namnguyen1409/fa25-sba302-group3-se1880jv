package sba.group3.backendmvc.dto.response.appointment;

import sba.group3.backendmvc.dto.response.organization.RoomResponse;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.entity.appointment.QueuePriority;
import sba.group3.backendmvc.entity.appointment.QueueStatus;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.appointment.QueueTicket}
 */
public record QueueTicketResponse(UUID id, UUID appointmentId, StaffResponse assignedDoctor, RoomResponse assignedRoom,
                                  String queueNumber, QueueStatus status, QueuePriority priority,
                                  UUID examinationId) implements Serializable {
}