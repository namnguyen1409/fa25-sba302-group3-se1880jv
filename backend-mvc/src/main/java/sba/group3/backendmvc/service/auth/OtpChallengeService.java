package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.entity.auth.OtpChallenge;
import sba.group3.backendmvc.entity.user.User;

import java.util.UUID;

public interface OtpChallengeService {
    OtpChallenge create(User user, MfaConfig config);

    void verify(User user, MfaConfig config, UUID challengeId, String inputCode);
}
