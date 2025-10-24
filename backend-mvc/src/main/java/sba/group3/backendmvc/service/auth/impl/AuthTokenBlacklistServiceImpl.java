package sba.group3.backendmvc.service.auth.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.enums.CacheKey;
import sba.group3.backendmvc.repository.auth.AuthTokenBlacklistRepository;
import sba.group3.backendmvc.service.auth.AuthTokenBlacklistService;
import sba.group3.backendmvc.service.infrastructure.CacheService;

import java.time.Duration;
import java.time.Instant;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthTokenBlacklistServiceImpl implements AuthTokenBlacklistService {

    AuthTokenBlacklistRepository tokenBlacklistRepository;
    CacheService cacheService;


    @Override
    public boolean isBlackListed(String jti) {
        return cacheService.getOrCompute(
                CacheKey.BLACKLISTED_TOKENS.of(jti),
                Boolean.class,
                Duration.ofDays(7),
                () -> tokenBlacklistRepository.existsByJti(jti)
        );
    }

    @Override
    public boolean addToBlackList(String jti, Instant expiresAt, String reason) {
        try {
            tokenBlacklistRepository.save(
                    sba.group3.backendmvc.entity.auth.AuthTokenBlacklist.builder()
                            .jti(jti)
                            .reason(reason)
                            .revokedAt(Instant.now())
                            .expiresAt(expiresAt)
                            .build()
            );
            Duration ttl = Duration.between(Instant.now(), expiresAt);
            cacheService.put(CacheKey.BLACKLISTED_TOKENS.of(jti), true, ttl);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public void clearBlacklistCache(String jti) {
        cacheService.evict(CacheKey.BLACKLISTED_TOKENS.of(jti));
    }

}
