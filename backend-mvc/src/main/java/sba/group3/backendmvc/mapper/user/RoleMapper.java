package sba.group3.backendmvc.mapper.user;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.user.RoleNameResponse;
import sba.group3.backendmvc.entity.user.Role;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface RoleMapper {
    Role toEntity(RoleNameResponse roleNameResponse);

    RoleNameResponse toDto(Role role);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Role partialUpdate(RoleNameResponse roleNameResponse, @MappingTarget Role role);
}