package sba.group3.backendmvc.service.user.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.response.user.AccountSettingResponse;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.mapper.user.UserMapper;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.user.AccountSettingsService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountSettingsServiceImpl implements AccountSettingsService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional
    @Override
    public AccountSettingResponse getAccountSettings(UUID userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));
        return userMapper.toDto1(user);
    }

}
