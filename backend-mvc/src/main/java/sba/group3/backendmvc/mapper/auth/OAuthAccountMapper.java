package sba.group3.backendmvc.mapper.auth;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.auth.OAuthAccountResponse;
import sba.group3.backendmvc.entity.auth.OAuthAccount;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface OAuthAccountMapper {
    OAuthAccount toEntity(OAuthAccountResponse OAuthAccountResponse);

    OAuthAccountResponse toDto(OAuthAccount OAuthAccount);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    OAuthAccount partialUpdate(OAuthAccountResponse OAuthAccountResponse, @MappingTarget OAuthAccount OAuthAccount);
}