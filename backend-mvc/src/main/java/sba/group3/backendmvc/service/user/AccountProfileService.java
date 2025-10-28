package sba.group3.backendmvc.service.user;

import org.springframework.web.multipart.MultipartFile;
import sba.group3.backendmvc.dto.request.user.UserProfileRequest;
import sba.group3.backendmvc.dto.response.user.UserProfileResponse;

import java.util.UUID;

public interface AccountProfileService {
    UserProfileResponse getProfile(UUID userId);

    UserProfileResponse updateProfile(UUID userId, UserProfileRequest userProfileRequest);

    UserProfileResponse updateAvatar(UUID userId, MultipartFile file) throws Exception;
}
