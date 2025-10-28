package sba.group3.backendmvc.service.user;

import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.controller.user.AccountSettingsController;
import sba.group3.backendmvc.dto.response.user.AccountSettingResponse;

import java.util.UUID;

public interface AccountSettingsService {
    AccountSettingResponse getAccountSettings(UUID userId);
    AccountSettingResponse updateUsername(UUID userId, AccountSettingsController.UpdateUsernameRequest newUsername);

    void requestEmailChange(UUID uuid, AccountSettingsController.UpdateEmailRequest updateEmailRequest);

    @Transactional
    void verifyEmailChange(String token);
}
