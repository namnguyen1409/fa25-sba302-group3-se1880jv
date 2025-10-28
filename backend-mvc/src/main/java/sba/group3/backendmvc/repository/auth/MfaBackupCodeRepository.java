package sba.group3.backendmvc.repository.auth;

import sba.group3.backendmvc.entity.auth.MfaBackupCode;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface MfaBackupCodeRepository extends BaseRepository<MfaBackupCode, UUID> {
    List<MfaBackupCode> findAllByUserId(UUID userId);

    void deleteAllByUser_Id(UUID userId);
}