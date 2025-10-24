package sba.group3.backendmvc.service.auth;

import java.time.Instant;

public interface AuthTokenBlacklistService {
    boolean isBlackListed(String jti);

    boolean addToBlackList(String jti, Instant expiresAt, String reason);

    void clearBlacklistCache(String jti);
}
