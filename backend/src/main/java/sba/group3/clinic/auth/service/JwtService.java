package sba.group3.clinic.auth.service;


import sba.group3.clinic.auth.security.CustomUserDetails;

import java.util.UUID;

public interface JwtService {
    String generateAccessToken(CustomUserDetails userDetails);

    String generateRefreshToken(CustomUserDetails userDetails);

    UUID getUserId(String token);

    String getJti(String token);

    boolean isValidToken(String token, CustomUserDetails user);
}
