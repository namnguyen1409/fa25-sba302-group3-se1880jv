package sba.group3.backendmvc.mapper.appointment;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.appointment.QueueTicketResponse;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.mapper.organization.RoomMapper;
import sba.group3.backendmvc.mapper.staff.StaffMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {StaffMapper.class, RoomMapper.class})
public interface QueueTicketMapper {
    @Mapping(source = "examinationId", target = "examination.id")
    @Mapping(source = "appointmentId", target = "appointment.id")
    QueueTicket toEntity(QueueTicketResponse queueTicketResponse);

    @InheritInverseConfiguration(name = "toEntity")
    QueueTicketResponse toDto(QueueTicket queueTicket);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    QueueTicket partialUpdate(QueueTicketResponse queueTicketResponse, @MappingTarget QueueTicket queueTicket);
}