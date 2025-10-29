package sba.group3.backendmvc.mapper.auth;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.auth.LoginAttemptResponse;
import sba.group3.backendmvc.entity.auth.LoginAttempt;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LoginAttemptMapper {
    LoginAttempt toEntity(LoginAttemptResponse loginAttemptResponse);

    LoginAttemptResponse toDto(LoginAttempt loginAttempt);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LoginAttempt partialUpdate(LoginAttemptResponse loginAttemptResponse, @MappingTarget LoginAttempt loginAttempt);
}