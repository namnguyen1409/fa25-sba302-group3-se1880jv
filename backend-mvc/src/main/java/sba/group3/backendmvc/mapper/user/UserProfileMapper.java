package sba.group3.backendmvc.mapper.user;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.user.UserProfileRequest;
import sba.group3.backendmvc.dto.response.user.SimpleProfileResponse;
import sba.group3.backendmvc.dto.response.user.UserProfileResponse;
import sba.group3.backendmvc.entity.user.UserProfile;
import sba.group3.backendmvc.mapper.common.AddressMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {AddressMapper.class})
public interface UserProfileMapper {

    SimpleProfileResponse toDto(UserProfile userProfile);

    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "userUsername", source = "user.username")
    UserProfileResponse toDto1(UserProfile userProfile);

    UserProfile toEntity(UserProfileRequest userProfileRequest);

    UserProfileRequest toDto2(UserProfile userProfile);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserProfile partialUpdate(UserProfileRequest userProfileRequest, @MappingTarget UserProfile userProfile);

    UserProfile toEntity(SimpleProfileResponse simpleProfileResponse);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    UserProfile partialUpdate(SimpleProfileResponse simpleProfileResponse, @MappingTarget UserProfile userProfile);
}