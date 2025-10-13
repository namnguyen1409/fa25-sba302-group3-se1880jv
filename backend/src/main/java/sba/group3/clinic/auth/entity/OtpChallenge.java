package sba.group3.clinic.auth.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.common.enums.MfaType;
import sba.group3.clinic.user.entity.User;

import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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

    @Column(name = "verified", nullable = false)
    boolean verified = false;

}