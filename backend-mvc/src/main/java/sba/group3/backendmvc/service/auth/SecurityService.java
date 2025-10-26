package sba.group3.backendmvc.service.auth;

import org.springframework.security.core.Authentication;
import sba.group3.backendmvc.entity.user.User;

public interface SecurityService {
    Authentication getAuthentication();

    User getCurrentUser();

    boolean hasRole(String role);

    boolean hasAnyRole(String... roles);
}
