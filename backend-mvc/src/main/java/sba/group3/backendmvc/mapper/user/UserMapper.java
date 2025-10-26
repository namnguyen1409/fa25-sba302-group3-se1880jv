package sba.group3.backendmvc.mapper.user;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.user.MeResponse;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.entity.user.UserProfile;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserProfileMapper.class})
public interface UserMapper {
    User toEntity(MeResponse meResponse);

    @AfterMapping
    default void linkUserProfile(@MappingTarget User user) {
        UserProfile userProfileResponse = user.getUserProfile();
        if (userProfileResponse != null) {
            userProfileResponse.setUser(user);
        }
    }

    MeResponse toDto(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(MeResponse meResponse, @MappingTarget User user);
}