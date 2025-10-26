package sba.group3.backendmvc.mapper.common;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.common.AdministrativeUnitResponse;
import sba.group3.backendmvc.entity.common.AdministrativeUnit;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AdministrativeUnitMapper {
    AdministrativeUnit toEntity(AdministrativeUnitResponse administrativeUnitResponse);

    AdministrativeUnitResponse toDto(AdministrativeUnit administrativeUnit);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    AdministrativeUnit partialUpdate(AdministrativeUnitResponse administrativeUnitResponse, @MappingTarget AdministrativeUnit administrativeUnit);
}