package sba.group3.backendmvc.repository.auth;

import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.enums.MfaType;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MfaConfigRepository extends BaseRepository<MfaConfig, UUID> {
    List<MfaConfig> findAllByUserId(UUID userId);
    Optional<MfaConfig> findByCredentialId(String credentialId);
    List<MfaConfig> findAllByUserIdAndMfaType(UUID userId, MfaType type);
}