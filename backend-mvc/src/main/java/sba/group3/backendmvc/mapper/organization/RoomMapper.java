package sba.group3.backendmvc.mapper.organization;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.organization.RoomRequest;
import sba.group3.backendmvc.dto.response.organization.RoomResponse;
import sba.group3.backendmvc.entity.organization.Room;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoomMapper {
    Room toEntity(RoomResponse roomResponse);

    RoomResponse toDto(Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Room partialUpdate(RoomResponse roomResponse, @MappingTarget Room room);

    Room toEntity(RoomRequest roomRequest);

    RoomRequest toDto1(Room room);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Room partialUpdate(RoomRequest roomRequest, @MappingTarget Room room);
}