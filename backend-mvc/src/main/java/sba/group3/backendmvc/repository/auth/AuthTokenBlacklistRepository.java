package sba.group3.backendmvc.repository.auth;

import sba.group3.backendmvc.entity.auth.AuthTokenBlacklist;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface AuthTokenBlacklistRepository extends BaseRepository<AuthTokenBlacklist, UUID> {
    boolean existsByJti(String jti);

    Optional<AuthTokenBlacklist> findByJti(String jti);
}