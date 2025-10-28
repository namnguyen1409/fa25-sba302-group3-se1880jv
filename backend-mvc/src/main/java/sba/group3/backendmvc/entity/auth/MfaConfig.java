package sba.group3.backendmvc.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.MfaType;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(
        name = "mfa_config",
        schema = "authentication"
)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class MfaConfig extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "mfa_type", nullable = false, length = 32)
    private MfaType mfaType;

    @Column(name = "secret")
    private String secret;

    @Column(name = "contact")
    private String contact;

    @Column(name = "is_primary", nullable = false)
    private Boolean primary = false;

    @Column(name = "last_verified_at")
    private Instant lastVerifiedAt;

    @Column(name = "credential_id", length = 200, unique = true)
    private String credentialId;

    @Column(name = "public_key", columnDefinition = "bytea")
    private byte[] publicKey;

    @Column(name = "sign_count")
    private Long signCount;

    @Column(name = "aaguid", length = 100)
    private String aaguid;

    @Column(name = "device_name")
    private String deviceName;

    @Column(name = "attestation_format")
    private String attestationFormat;

    @Column(name = "revoked", nullable = false)
    private boolean revoked = false;
}