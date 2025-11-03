package sba.group3.backendmvc.mapper.staff;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.staff.PositionRequest;
import sba.group3.backendmvc.dto.response.staff.PositionResponse;
import sba.group3.backendmvc.entity.staff.Position;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PositionMapper {
    Position toEntity(PositionResponse positionResponse);

    PositionResponse toDto(Position position);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Position partialUpdate(PositionResponse positionResponse, @MappingTarget Position position);

    Position toEntity(PositionRequest positionRequest);

    PositionRequest toDto1(Position position);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Position partialUpdate(PositionRequest positionRequest, @MappingTarget Position position);
}