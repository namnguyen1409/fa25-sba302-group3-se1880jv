package sba.group3.backendmvc.mapper.staff;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.staff.SpecialtyRequest;
import sba.group3.backendmvc.dto.response.staff.SpecialtyResponse;
import sba.group3.backendmvc.entity.staff.Specialty;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecialtyMapper {
    @Mapping(source = "departmentId", target = "department.id")
    Specialty toEntity(SpecialtyRequest specialtyRequest);

    @Mapping(source = "department.id", target = "departmentId")
    SpecialtyRequest toDto(Specialty specialty);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "departmentId", target = "department.id")
    Specialty partialUpdate(SpecialtyRequest specialtyRequest, @MappingTarget Specialty specialty);

    Specialty toEntity(SpecialtyResponse specialtyResponse);

    SpecialtyResponse toDto1(Specialty specialty);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Specialty partialUpdate(SpecialtyResponse specialtyResponse, @MappingTarget Specialty specialty);
}