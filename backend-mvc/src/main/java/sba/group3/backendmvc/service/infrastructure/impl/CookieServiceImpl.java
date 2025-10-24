package sba.group3.backendmvc.service.infrastructure.impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.service.infrastructure.CookieService;

import java.time.Duration;
import java.util.Arrays;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CookieServiceImpl implements CookieService {

    HttpServletResponse httpServletResponse;
    HttpServletRequest httpServletRequest;
    @NonFinal
    @Value("${security.jwt.refresh-token.cookie-name}")
    String refreshTokenCookieName;
    @NonFinal
    @Value("${security.jwt.refresh-token.expiration}")
    Duration refreshTokenExpiration;

    @Override
    public void addRefreshTokenCookie(String token) {
        addRefreshTokenCookie(token, refreshTokenExpiration);
    }

    @Override
    public void addRefreshTokenCookie(String token, Duration customExpiration) {
        Cookie cookie = new Cookie(refreshTokenCookieName, token);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge((int) customExpiration.toSeconds());
        httpServletResponse.addCookie(cookie);
    }

    @Override
    public void clearRefreshTokenCookie() {
        Cookie cookie = new Cookie(refreshTokenCookieName, null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        httpServletResponse.addCookie(cookie);
    }

    @Override
    public Optional<String> getRefreshToken() {
        if (httpServletRequest.getCookies() == null) return Optional.empty();
        return Arrays.stream(httpServletRequest.getCookies())
                .filter(c -> c.getName().equals(refreshTokenCookieName))
                .map(Cookie::getValue)
                .findFirst();
    }

}
