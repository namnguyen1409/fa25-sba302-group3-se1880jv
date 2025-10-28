package sba.group3.backendmvc.service.user.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import sba.group3.backendmvc.dto.request.user.UserProfileRequest;
import sba.group3.backendmvc.dto.response.user.UserProfileResponse;
import sba.group3.backendmvc.mapper.user.UserProfileMapper;
import sba.group3.backendmvc.repository.user.UserProfileRepository;
import sba.group3.backendmvc.service.infrastructure.FileUploadService;
import sba.group3.backendmvc.service.user.AccountProfileService;

import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AccountProfileServiceImpl implements AccountProfileService {

    UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;
    private final FileUploadService fileUploadService;

    @Override
    public UserProfileResponse getProfile(UUID userId) {
        return userProfileMapper.toDto1(userProfileRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("User profile not found")));

    }

    @Transactional
    @Override
    public UserProfileResponse updateProfile(UUID userId, UserProfileRequest userProfileRequest) {
        var userProfile = userProfileRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("User profile not found"));
        userProfileMapper.partialUpdate(userProfileRequest, userProfile);
        var updatedProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toDto1(updatedProfile);
    }

    @Transactional
    @Override
    public UserProfileResponse updateAvatar(UUID userId, MultipartFile file) throws Exception {
        var avatarUrl = fileUploadService.upload(file, "PROFILE", userId.toString());
        var userProfile = userProfileRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("User profile not found"));
        userProfile.setAvatarUrl(avatarUrl);
        var updatedProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toDto1(updatedProfile);
    }

}
