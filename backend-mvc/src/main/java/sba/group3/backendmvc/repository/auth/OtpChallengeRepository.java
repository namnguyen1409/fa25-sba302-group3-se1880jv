package sba.group3.backendmvc.repository.auth;

import org.springframework.data.jpa.repository.Query;
import sba.group3.backendmvc.entity.auth.OtpChallenge;
import sba.group3.backendmvc.enums.MfaType;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface OtpChallengeRepository extends BaseRepository<OtpChallenge, UUID> {

    @Query("""
                select o from OtpChallenge o
                    where o.user.id = :userId
                        and o.mfaType = :mfaType
                        and o.verified = false
                        and o.deleted = false
                        and o.expiresAt > current_timestamp
            """)
    Optional<OtpChallenge> findActiveByUserAndType(UUID userId, MfaType mfaType);
}