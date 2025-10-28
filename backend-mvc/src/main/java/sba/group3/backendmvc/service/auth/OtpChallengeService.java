package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.entity.auth.OtpChallenge;
import sba.group3.backendmvc.entity.user.User;

import java.util.Map;
import java.util.UUID;

public interface OtpChallengeService {
    OtpChallenge create(User user, MfaConfig config);

    OtpChallenge create(User user, MfaConfig config, Map<String, Object> metadata);

    OtpChallenge createEmailVerification(User user, String newEmail);

    void verifyEmailChange(String token);

    OtpChallenge verify(User user, MfaConfig config, UUID challengeId, String inputCode);

}
