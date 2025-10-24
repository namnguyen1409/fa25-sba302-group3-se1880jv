package sba.group3.backendmvc.entity.auth;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "auth_token_blacklist", schema = "authentication", indexes = {
        @Index(name = "idx_tokenblacklist_jti_unq", columnList = "jti", unique = true)
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class AuthTokenBlacklist extends BaseEntity {
    @Column(name = "jti", nullable = false, unique = true, length = 128)
    String jti;

    @Column(name = "reason")
    String reason;

    @Column(name = "revoked_at")
    Instant revokedAt;

    @Column(name = "expires_at")
    Instant expiresAt;

}