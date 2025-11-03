package sba.group3.backendmvc.mapper.organization;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.organization.ClinicRequest;
import sba.group3.backendmvc.dto.response.organization.ClinicResponse;
import sba.group3.backendmvc.dto.response.organization.ClinicSimpleResponse;
import sba.group3.backendmvc.entity.organization.Clinic;
import sba.group3.backendmvc.mapper.common.AddressMapper;

@Mapper(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        componentModel = MappingConstants.ComponentModel.SPRING,
        uses = {AddressMapper.class}
)
public interface ClinicMapper {
    Clinic toEntity(ClinicResponse clinicResponse);

    ClinicResponse toDto(Clinic clinic);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Clinic partialUpdate(ClinicResponse clinicResponse, @MappingTarget Clinic clinic);

    Clinic toEntity(ClinicRequest clinicRequest);

    ClinicRequest toDto1(Clinic clinic);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Clinic partialUpdate(ClinicRequest clinicRequest, @MappingTarget Clinic clinic);

    Clinic toEntity(ClinicSimpleResponse clinicSimpleResponse);

    ClinicSimpleResponse toSimpleResponse(Clinic clinic);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Clinic partialUpdate(ClinicSimpleResponse clinicSimpleResponse, @MappingTarget Clinic clinic);
}