package sba.group3.backendmvc.service.auth;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.auth.LoginAttemptResponse;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.LoginStatus;

import java.time.Duration;

public interface LoginAttemptService {

    void recordAttempt(User user, String ip, String userAgent, LoginStatus status, String loginMethod);

    long countFailures(User user, Duration window);

    Page<LoginAttemptResponse> filter(SearchFilter filter);
}
