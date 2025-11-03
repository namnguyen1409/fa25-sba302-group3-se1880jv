package sba.group3.backendmvc.mapper.organization;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.organization.DepartmentRequest;
import sba.group3.backendmvc.dto.response.organization.DepartmentResponse;
import sba.group3.backendmvc.dto.response.organization.DepartmentSimpleResponse;
import sba.group3.backendmvc.entity.organization.Department;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {RoomMapper.class}
)
public interface DepartmentMapper {
    Department toEntity(DepartmentResponse departmentResponse);

    DepartmentResponse toDto(Department department);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department partialUpdate(DepartmentResponse departmentResponse, @MappingTarget Department department);

    Department toEntity(DepartmentRequest departmentRequest);

    DepartmentRequest toDto1(Department department);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department partialUpdate(DepartmentRequest departmentRequest, @MappingTarget Department department);

    Department toEntity(DepartmentSimpleResponse departmentSimpleResponse);

    DepartmentSimpleResponse toSimpleResponse(Department department);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Department partialUpdate(DepartmentSimpleResponse departmentSimpleResponse, @MappingTarget Department department);
}