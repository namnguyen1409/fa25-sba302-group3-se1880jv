package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.dto.request.auth.TOTPConfirmRequest;
import sba.group3.backendmvc.dto.response.auth.MfaSetupResponse;
import sba.group3.backendmvc.entity.auth.MfaConfig;

import java.util.List;
import java.util.UUID;

public interface MfaConfigService {
    List<MfaConfig> findAllByUserId(UUID userId);

    MfaSetupResponse setupTotpMfa(UUID userId);

    void confirmTotpMfa(UUID userId, TOTPConfirmRequest request);

    boolean verifyTotpMfa(UUID userId, String code);
}
