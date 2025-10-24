package sba.group3.backendmvc.service.infrastructure;

import java.time.Duration;
import java.util.Optional;

public interface CookieService {
    void addRefreshTokenCookie(String token);

    void addRefreshTokenCookie(String token, Duration customExpiration);

    void clearRefreshTokenCookie();

    Optional<String> getRefreshToken();
}
