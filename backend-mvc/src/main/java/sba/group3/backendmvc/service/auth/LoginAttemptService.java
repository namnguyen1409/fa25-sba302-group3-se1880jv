package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.LoginStatus;

import java.time.Duration;

public interface LoginAttemptService {

    void recordAttempt(User user, String ip, String userAgent, LoginStatus status, String loginMethod);

    long countFailures(User user, Duration window);
}
