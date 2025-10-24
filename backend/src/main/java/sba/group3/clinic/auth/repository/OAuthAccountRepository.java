package sba.group3.clinic.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sba.group3.clinic.auth.entity.OAuthAccount;
import sba.group3.clinic.common.enums.OAuthProvider;

import java.util.Optional;
import java.util.UUID;

public interface OAuthAccountRepository extends JpaRepository<OAuthAccount, UUID> {
    Optional<OAuthAccount> findByProviderAndProviderUserId(OAuthProvider provider, String providerUserId);
}