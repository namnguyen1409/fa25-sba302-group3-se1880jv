package sba.group3.backendmvc.service.user.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.response.user.UserProfileResponse;
import sba.group3.backendmvc.mapper.user.UserProfileMapper;
import sba.group3.backendmvc.repository.user.UserProfileRepository;
import sba.group3.backendmvc.service.user.AccountProfileService;

import java.util.UUID;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class AccountProfileServiceImpl implements AccountProfileService {

    private final UserProfileRepository userProfileRepository;
    UserProfileMapper userProfileMapper;

    @Override
    public UserProfileResponse getProfile(UUID userId) {
         return userProfileMapper.toDto1(userProfileRepository.findByUser_Id(userId)
                 .orElseThrow(() -> new RuntimeException("User profile not found")));

    }

}
