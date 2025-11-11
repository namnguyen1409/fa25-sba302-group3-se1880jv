package sba.group3.backendmvc.service.auth;

import org.springframework.security.oauth2.jwt.Jwt;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.service.auth.impl.JwtServiceImpl;

import java.time.Instant;

public interface JwtService {
    String generateAccessToken(User user, String jwtId, String deviceId);

    String generateAccessToken(User user, String jwtId, String deviceId, Instant customExpiration);

    String generateRefreshToken(User user, String jwtId, String deviceId);

    String generateRefreshToken(User user, String jwtId, String deviceId, Instant customExpiration);

    JwtServiceImpl.AuthInfo extract(Jwt jwt);
}
