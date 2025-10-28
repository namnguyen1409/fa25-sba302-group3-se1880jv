package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.entity.user.User;

import java.util.List;
import java.util.UUID;

public interface MfaBackupCodeService {
    List<String> generateBackupCodes(UUID userId);

    boolean verifyBackupCode(User user, String rawCode);
}
