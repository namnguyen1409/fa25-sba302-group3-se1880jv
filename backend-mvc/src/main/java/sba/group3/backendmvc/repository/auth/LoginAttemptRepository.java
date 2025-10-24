package sba.group3.backendmvc.repository.auth;

import sba.group3.backendmvc.entity.auth.LoginAttempt;
import sba.group3.backendmvc.enums.LoginStatus;
import sba.group3.backendmvc.repository.BaseRepository;

import java.time.Instant;
import java.util.UUID;

public interface LoginAttemptRepository extends BaseRepository<LoginAttempt, UUID> {
    long countByUserIdAndStatusAndCreatedDateAfter(UUID userId, LoginStatus status, Instant createdDateAfter);
}