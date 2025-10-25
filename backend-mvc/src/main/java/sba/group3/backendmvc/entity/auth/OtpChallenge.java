package sba.group3.backendmvc.entity.auth;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.MfaType;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "otp_challenge", schema = "authentication", indexes = {
        @Index(name = "idx_otpchallenge_user_id", columnList = "user_id"),
        @Index(name = "idx_otpchallenge_mfa_type", columnList = "mfa_type")
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class OtpChallenge extends BaseEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "mfa_type", nullable = false, length = 30)
    MfaType mfaType;

    @Column(name = "code", nullable = false, length = 10)
    String code;

    @Column(name = "expires_at", nullable = false)
    Instant expiresAt;

    @Column(name = "attempt_count", nullable = false)
    Integer attemptCount;

    @Builder.Default
    @Column(name = "verified", nullable = false)
    boolean verified = false;

}