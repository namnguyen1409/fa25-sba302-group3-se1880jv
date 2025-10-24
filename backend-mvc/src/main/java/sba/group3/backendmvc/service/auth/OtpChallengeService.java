package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.entity.auth.OtpChallenge;
import sba.group3.backendmvc.entity.user.User;

public interface OtpChallengeService {
    OtpChallenge create(User user, MfaConfig config);
}
