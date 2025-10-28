package sba.group3.backendmvc.mapper.auth;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.auth.MfaConfigResponse;
import sba.group3.backendmvc.entity.auth.MfaConfig;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MfaConfigMapper {
    MfaConfig toEntity(MfaConfigResponse mfaConfigResponse);

    MfaConfigResponse toDto(MfaConfig mfaConfig);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    MfaConfig partialUpdate(MfaConfigResponse mfaConfigResponse, @MappingTarget MfaConfig mfaConfig);
}