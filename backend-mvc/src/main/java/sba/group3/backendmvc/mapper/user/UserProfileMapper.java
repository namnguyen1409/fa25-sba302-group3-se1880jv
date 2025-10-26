package sba.group3.backendmvc.mapper.user;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.user.SimpleProfileResponse;
import sba.group3.backendmvc.dto.response.user.UserProfileResponse;
import sba.group3.backendmvc.entity.user.UserProfile;
import sba.group3.backendmvc.mapper.common.AddressMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {AddressMapper.class})
public interface UserProfileMapper {

    SimpleProfileResponse toDto(UserProfile userProfile);

    @Mapping(target = "userPhone", source = "user.phone")
    @Mapping(target = "userEmail", source = "user.email")
    @Mapping(target = "userUsername", source = "user.username")
    UserProfileResponse toDto1(UserProfile userProfile);

}