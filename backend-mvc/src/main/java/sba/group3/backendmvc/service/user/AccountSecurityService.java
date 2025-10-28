package sba.group3.backendmvc.service.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.controller.user.AccountSecurityController;
import sba.group3.backendmvc.dto.response.auth.MfaConfigResponse;
import sba.group3.backendmvc.dto.response.user.DeviceSessionResponse;

import java.util.List;
import java.util.UUID;

public interface AccountSecurityService {
    void changePassword(UUID userId, AccountSecurityController.ChangePasswordRequest request);

    void enableMfa(UUID userId);

    void disableMfa(UUID userId, AccountSecurityController.MfaDisableRequest request);

    @Transactional
    void deleteMfaConfig(UUID userId, AccountSecurityController.MfaDeleteRequest request);

    List<MfaConfigResponse> getMfaConfigsByUserId(UUID userId);

    AccountSecurityController.MfaInitResponse initEmailMfa(UUID userId, String contact);

    MfaConfigResponse confirmEmailMfa(UUID uuid, AccountSecurityController.MfaConfirmRequest request);


    List<String> generateBackupCodes(UUID userId);
}
