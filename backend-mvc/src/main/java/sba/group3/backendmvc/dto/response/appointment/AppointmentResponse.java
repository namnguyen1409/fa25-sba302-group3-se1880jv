package sba.group3.backendmvc.dto.response.appointment;

import sba.group3.backendmvc.dto.response.patient.PatientResponse;
import sba.group3.backendmvc.dto.response.staff.SpecialtyResponse;
import sba.group3.backendmvc.entity.appointment.AppointmentSource;
import sba.group3.backendmvc.entity.appointment.AppointmentStatus;
import sba.group3.backendmvc.entity.appointment.AppointmentType;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.appointment.Appointment}
 */
public record AppointmentResponse(UUID id, PatientResponse patient, SpecialtyResponse specialty, AppointmentType type,
                                  AppointmentStatus status, AppointmentSource source, String note,
                                  QueueTicketResponse queueTicket, Instant createdDate) implements Serializable {
}