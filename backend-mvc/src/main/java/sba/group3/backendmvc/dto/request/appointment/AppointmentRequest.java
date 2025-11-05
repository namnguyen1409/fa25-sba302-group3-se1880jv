package sba.group3.backendmvc.dto.request.appointment;

import sba.group3.backendmvc.entity.appointment.AppointmentSource;
import sba.group3.backendmvc.entity.appointment.AppointmentStatus;
import sba.group3.backendmvc.entity.appointment.AppointmentType;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.appointment.Appointment}
 */
public record AppointmentRequest(UUID patientId, UUID specialtyId, AppointmentType type, AppointmentStatus status,
                                 AppointmentSource source, String note) implements Serializable {
}