package sba.group3.backendmvc.repository.auth;

import sba.group3.backendmvc.entity.auth.OAuthAccount;
import sba.group3.backendmvc.enums.OAuthProvider;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface OAuthAccountRepository extends BaseRepository<OAuthAccount, UUID> {
    Optional<OAuthAccount> findByProviderAndProviderUserId(OAuthProvider provider, String providerUserId);

    boolean existsByProviderAndProviderUserId(OAuthProvider provider, String providerUserId);
}