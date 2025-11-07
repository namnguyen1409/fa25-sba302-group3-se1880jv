package sba.group3.backendmvc.mapper.staff;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.staff.StaffRequest;
import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.entity.staff.Staff;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {SpecialtyMapper.class, PositionMapper.class})
public interface StaffMapper {
    @Mapping(source = "positionId", target = "position.id")
    @Mapping(source = "specialtyId", target = "specialty.id")
    @Mapping(source = "departmentId", target = "department.id")
    Staff toEntity(StaffRequest staffRequest);

    @InheritInverseConfiguration(name = "toEntity")
    StaffRequest toDto(Staff staff);

    @Mapping(target = "department", ignore = true)
    @Mapping(target = "specialty", ignore = true)
    @Mapping(target = "position", ignore = true)
    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Staff partialUpdate(StaffRequest staffRequest, @MappingTarget Staff staff);

    Staff toEntity(StaffResponse staffResponse);

    StaffResponse toDto1(Staff staff);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Staff partialUpdate(StaffResponse staffResponse, @MappingTarget Staff staff);
}