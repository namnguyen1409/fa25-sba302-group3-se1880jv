package sba.group3.backendmvc.repository.auth;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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


    @Modifying
    @Query("update MfaConfig c set c.signCount = :signCount where c.user.id = :userId and c.mfaType = :type and c.credentialId = :credId")
    void updateSignCount(@Param("userId") UUID userId,
                         @Param("type") MfaType type,
                         @Param("credId") String credentialId,
                         @Param("signCount") long signCount);
}