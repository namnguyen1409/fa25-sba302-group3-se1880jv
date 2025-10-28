package sba.group3.backendmvc.service.user.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.controller.user.AccountSettingsController;
import sba.group3.backendmvc.dto.response.user.AccountSettingResponse;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.user.UserMapper;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.OtpChallengeService;
import sba.group3.backendmvc.service.user.AccountSettingsService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountSettingsServiceImpl implements AccountSettingsService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final OtpChallengeService otpChallengeService;

    @Transactional
    @Override
    public AccountSettingResponse getAccountSettings(UUID userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toDto1(user);
    }

    @Transactional
    @Override
    public AccountSettingResponse updateUsername(UUID userId, AccountSettingsController.UpdateUsernameRequest request) {
        if (userRepository.existsByUsername(request.newUsername())) {
            throw new AppException(ErrorCode.USERNAME_ALREADY_EXISTS);
        }
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        if (!user.isFirstLogin()) {
            throw new AppException(ErrorCode.USERNAME_CANNOT_BE_CHANGED);
        }
        user.setUsername(request.newUsername());
        user.setFirstLogin(false);
        return userMapper.toDto1(userRepository.save(user));
    }

    @Transactional
    @Override
    public void requestEmailChange(UUID uuid, AccountSettingsController.UpdateEmailRequest updateEmailRequest) {
        var user = userRepository.findById(uuid)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        String newEmail = updateEmailRequest.newEmail();
        if (userRepository.existsByEmail(newEmail)) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }
        otpChallengeService.createEmailVerification(user, newEmail);

    }


    @Transactional
    @Override
    public void verifyEmailChange(String token) {
        otpChallengeService.verifyEmailChange(token);
    }


}
