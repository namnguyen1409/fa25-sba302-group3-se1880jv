package sba.group3.backendmvc.mapper.appointment;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.appointment.QueueTicketResponse;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.mapper.organization.RoomMapper;
import sba.group3.backendmvc.mapper.patient.PatientMapper;
import sba.group3.backendmvc.mapper.staff.SpecialtyMapper;
import sba.group3.backendmvc.mapper.staff.StaffMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {StaffMapper.class, RoomMapper.class, PatientMapper.class, SpecialtyMapper.class})
public interface QueueTicketMapper {
    @Mapping(source = "appointmentId", target = "appointment.id")
    @Mapping(source = "examinationId", target = "examination.id")
    QueueTicket toEntity(QueueTicketResponse queueTicketResponse);

    @Mapping(source = "appointment.patient", target = "appointmentPatient")
    @Mapping(source = "appointment.specialty", target = "appointmentSpecialty")
    @Mapping(source = "appointment.id", target = "appointmentId")
    @Mapping(source = "examination.id", target = "examinationId")
    @InheritInverseConfiguration(name = "toEntity")
    QueueTicketResponse toDto(QueueTicket queueTicket);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    QueueTicket partialUpdate(QueueTicketResponse queueTicketResponse, @MappingTarget QueueTicket queueTicket);
}