package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.entity.user.User;

import java.time.Instant;

public interface JwtService {
    String generateAccessToken(User user, String jwtId, String deviceId);

    String generateAccessToken(User user, String jwtId, String deviceId, Instant customExpiration);

    String generateRefreshToken(User user, String jwtId, String deviceId);

    String generateRefreshToken(User user, String jwtId, String deviceId, Instant customExpiration);
}
