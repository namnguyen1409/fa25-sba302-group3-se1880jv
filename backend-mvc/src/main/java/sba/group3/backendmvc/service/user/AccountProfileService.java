package sba.group3.backendmvc.service.user;

import sba.group3.backendmvc.dto.response.user.UserProfileResponse;

import java.util.UUID;

public interface AccountProfileService {
    UserProfileResponse getProfile(UUID userId);
}
