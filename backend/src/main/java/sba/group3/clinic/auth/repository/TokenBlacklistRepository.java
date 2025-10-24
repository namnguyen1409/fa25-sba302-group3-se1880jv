package sba.group3.clinic.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import sba.group3.clinic.auth.entity.TokenBlacklist;

import java.util.UUID;

public interface TokenBlacklistRepository extends JpaRepository<TokenBlacklist, UUID>, JpaSpecificationExecutor<TokenBlacklist> {
    boolean existsByJti(String jti);
}