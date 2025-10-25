package sba.group3.backendmvc.mapper.common;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.common.AddressResponse;
import sba.group3.backendmvc.entity.common.Address;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AddressMapper {
    Address toEntity(AddressResponse addressResponse);

    AddressResponse toDto(Address address);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Address partialUpdate(AddressResponse addressResponse, @MappingTarget Address address);
}