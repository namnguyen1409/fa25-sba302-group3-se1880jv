package sba.group3.clinic.auth.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import sba.group3.clinic.common.entity.BaseEntity;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "token_blacklist", schema = "authentication", indexes = {
        @Index(name = "idx_tokenblacklist_jti_unq", columnList = "jti", unique = true)
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class TokenBlacklist extends BaseEntity {
    @Column(name = "jti", nullable = false, unique = true, length = 128)
    String jti;

    @Column(name = "reason")
    String reason;

    @Column(name = "revoked_at")
    Instant revokedAt;

    @Column(name = "expires_at")
    Instant expiresAt;

}