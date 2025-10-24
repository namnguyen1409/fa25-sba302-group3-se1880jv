package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.entity.auth.MfaConfig;

import java.util.List;
import java.util.UUID;

public interface MfaConfigService {
    List<MfaConfig> findAllByUserId(UUID userId);
}
