package sba.group3.backendmvc.service.auth.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.entity.auth.LoginAttempt;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.LoginStatus;
import sba.group3.backendmvc.repository.auth.LoginAttemptRepository;
import sba.group3.backendmvc.service.auth.LoginAttemptService;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class LoginAttemptServiceImpl implements LoginAttemptService {

    LoginAttemptRepository loginAttemptRepository;

    @Transactional
    @Override
    public void recordAttempt(User user, String ip, String userAgent, LoginStatus status, String loginMethod) {
        LoginAttempt attempt = LoginAttempt.builder()
                .user(user)
                .ipAddress(ip)
                .userAgent(userAgent)
                .status(status)
                .loginMethod(loginMethod)
                .build();
        loginAttemptRepository.save(attempt);
    }

    @Override
    public long countFailures(User user, Duration window) {
        Instant cutoff = Instant.now().minus(window);
        return loginAttemptRepository.countByUserIdAndStatusAndCreatedDateAfter(
                user.getId(),
                LoginStatus.FAILURE,
                cutoff
        );
    }

}
