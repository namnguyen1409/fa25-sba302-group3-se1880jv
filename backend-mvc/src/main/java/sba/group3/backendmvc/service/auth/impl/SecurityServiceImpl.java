package sba.group3.backendmvc.service.auth.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.SecurityService;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class SecurityServiceImpl implements SecurityService {

    private final UserRepository userRepository;

    @Override
    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }


    @Override
    public User getCurrentUser() {
        var auth = getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return null;
        }
        var principal = auth.getPrincipal();
        if (principal instanceof User user) {
            return user;
        }
        if (principal instanceof Jwt jwt) {
            var userId = UUID.fromString(jwt.getSubject());
            return userRepository.findById(userId)
                    .orElse(null);
        }
        return null;
    }

    @Override
    public boolean hasRole(String role) {
        var auth = getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return false;
        }
        return auth.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals(role));
    }

    @Override
    public boolean hasAnyRole(String... roles) {
        var auth = getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            return false;
        }
        for (String role : roles) {
            if (hasRole(role)) {
                return true;
            }
        }
        return false;
    }

}
