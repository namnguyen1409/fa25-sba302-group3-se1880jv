package sba.group3.backendmvc.service.user;

import sba.group3.backendmvc.dto.response.user.AccountSettingResponse;

import java.util.UUID;

public interface AccountSettingsService {
    AccountSettingResponse getAccountSettings(UUID userId);
}
