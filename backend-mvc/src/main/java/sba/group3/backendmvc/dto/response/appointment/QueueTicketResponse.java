package sba.group3.backendmvc.dto.response.appointment;

import sba.group3.backendmvc.dto.response.organization.RoomResponse;
import sba.group3.backendmvc.dto.response.patient.PatientResponse;
import sba.group3.backendmvc.dto.response.staff.SpecialtyResponse;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.entity.appointment.QueuePriority;
import sba.group3.backendmvc.entity.appointment.QueueStatus;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.appointment.QueueTicket}
 */
public record QueueTicketResponse(UUID id, StaffResponse assignedDoctor, RoomResponse assignedRoom,
                                  String queueNumber, QueueStatus status, QueuePriority priority,
                                  UUID examinationId, UUID appointmentId, PatientResponse appointmentPatient,
                                  SpecialtyResponse appointmentSpecialty) implements Serializable {
}