package sba.group3.backendmvc.repository.appointment;

import sba.group3.backendmvc.entity.appointment.Appointment;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface AppointmentRepository extends BaseRepository<Appointment, UUID> {
}