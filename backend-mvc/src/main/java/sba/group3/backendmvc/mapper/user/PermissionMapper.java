package sba.group3.backendmvc.mapper.user;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.user.PermissionNameResponse;
import sba.group3.backendmvc.entity.user.Permission;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PermissionMapper {
    Permission toEntity(PermissionNameResponse permissionNameResponse);

    PermissionNameResponse toDto(Permission permission);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Permission partialUpdate(PermissionNameResponse permissionNameResponse, @MappingTarget Permission permission);
}