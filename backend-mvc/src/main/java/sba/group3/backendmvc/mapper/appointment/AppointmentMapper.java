package sba.group3.backendmvc.mapper.appointment;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.appointment.AppointmentRequest;
import sba.group3.backendmvc.dto.response.appointment.AppointmentResponse;
import sba.group3.backendmvc.entity.appointment.Appointment;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.mapper.patient.PatientMapper;
import sba.group3.backendmvc.mapper.staff.SpecialtyMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PatientMapper.class, SpecialtyMapper.class, QueueTicketMapper.class})
public interface AppointmentMapper {
    @Mapping(source = "specialtyId", target = "specialty.id")
    @Mapping(source = "patientId", target = "patient.id")
    Appointment toEntity(AppointmentRequest appointmentRequest);

    @InheritInverseConfiguration(name = "toEntity")
    AppointmentRequest toDto(Appointment appointment);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Appointment partialUpdate(AppointmentRequest appointmentRequest, @MappingTarget Appointment appointment);

    Appointment toEntity(AppointmentResponse appointmentResponse);

    @AfterMapping
    default void linkQueueTicket(@MappingTarget Appointment appointment) {
        QueueTicket queueTicket = appointment.getQueueTicket();
        if (queueTicket != null) {
            queueTicket.setAppointment(appointment);
        }
    }

    AppointmentResponse toDto1(Appointment appointment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Appointment partialUpdate(AppointmentResponse appointmentResponse, @MappingTarget Appointment appointment);
}