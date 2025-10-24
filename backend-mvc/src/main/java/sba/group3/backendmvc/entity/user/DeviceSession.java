package sba.group3.backendmvc.entity.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;

import java.time.Instant;

@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "device_session",
        schema = "user_management",
        indexes = {
                @Index(name = "idx_devicesession_user_id", columnList = "user_id"),
                @Index(name = "idx_devicesession_device_id", columnList = "device_id")
        }, uniqueConstraints = {
        @UniqueConstraint(name = "uc_devicesession_user_id_device_id", columnNames = {"user_id", "device_id"})
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class DeviceSession extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "device_id", nullable = false, length = 100, unique = true)
    String deviceId;

    @Column(name = "device_name")
    String deviceName;

    @Column(name = "ip_address", length = 45)
    String ipAddress;

    @Column(name = "user_agent")
    String userAgent;

    @Builder.Default
    @Column(name = "trusted", nullable = false)
    boolean trusted = false;

    @Column(name = "access_token_id")
    String accessTokenId;

    @Column(name = "refresh_token_id")
    String refreshTokenId;

    @Column(name = "last_refreshed_at")
    Instant lastRefreshedAt;

    @Column(name = "expires_in")
    Instant expiresIn;

    @Builder.Default
    @Column(name = "revoked", nullable = false)
    boolean revoked = false;

    @Builder.Default
    @Column(name = "remember_me", nullable = false)
    boolean rememberMe = false;

    @Column(name = "last_login_at")
    private Instant lastLoginAt;

    @Builder.Default
    @Column(name = "refresh_count")
    private Long refreshCount = 0L;

}