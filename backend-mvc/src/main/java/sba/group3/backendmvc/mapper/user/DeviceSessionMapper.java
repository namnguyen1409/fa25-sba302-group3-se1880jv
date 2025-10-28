package sba.group3.backendmvc.mapper.user;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.user.DeviceInfo;
import sba.group3.backendmvc.dto.response.user.DeviceSessionResponse;
import sba.group3.backendmvc.entity.user.DeviceSession;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DeviceSessionMapper {
    DeviceSession toEntity(DeviceInfo deviceInfo);

    DeviceInfo toDto(DeviceSession deviceSession);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DeviceSession partialUpdate(DeviceInfo deviceInfo, @MappingTarget DeviceSession deviceSession);

    DeviceSession toEntity(DeviceSessionResponse deviceSessionResponse);

    DeviceSessionResponse toDto1(DeviceSession deviceSession);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DeviceSession partialUpdate(DeviceSessionResponse deviceSessionResponse, @MappingTarget DeviceSession deviceSession);
}