package sba.group3.backendmvc.mapper.user;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.user.AccountSettingResponse;
import sba.group3.backendmvc.dto.response.user.MeResponse;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.entity.user.UserProfile;
import sba.group3.backendmvc.mapper.auth.OAuthAccountMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {UserProfileMapper.class, OAuthAccountMapper.class})
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

    User toEntity(AccountSettingResponse accountSettingResponse);

    @AfterMapping
    default void linkOAuthAccounts(@MappingTarget User user) {
        user.getOAuthAccounts().forEach(oAuthAccount -> oAuthAccount.setUser(user));
    }

    @Mapping(target = "OAuthAccounts", source = "OAuthAccounts")
    AccountSettingResponse toDto1(User user);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    User partialUpdate(AccountSettingResponse accountSettingResponse, @MappingTarget User user);
}