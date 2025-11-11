package sba.group3.backendmvc.mapper.staff;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.staff.StaffScheduleRequest;
import sba.group3.backendmvc.dto.response.staff.StaffScheduleResponse;
import sba.group3.backendmvc.entity.staff.StaffSchedule;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface StaffScheduleMapper {
    @Mapping(source = "roomId", target = "room.id")
    @Mapping(source = "staffId", target = "staff.id")
    StaffSchedule toEntity(StaffScheduleRequest staffScheduleRequest);

    @Mapping(source = "room.id", target = "roomId")
    @Mapping(source = "staff.id", target = "staffId")
    StaffScheduleRequest toDto(StaffSchedule staffSchedule);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "staffId", target = "staff.id")
    StaffSchedule partialUpdate(StaffScheduleRequest staffScheduleRequest, @MappingTarget StaffSchedule staffSchedule);

    StaffSchedule toEntity(StaffScheduleResponse staffScheduleResponse);

    StaffScheduleResponse toDto1(StaffSchedule staffSchedule);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    StaffSchedule partialUpdate(StaffScheduleResponse staffScheduleResponse, @MappingTarget StaffSchedule staffSchedule);
}