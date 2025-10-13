package sba.group3.clinic.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import sba.group3.clinic.auth.entity.MfaConfig;
import sba.group3.clinic.auth.entity.OAuthAccount;
import sba.group3.clinic.common.entity.BaseEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users", schema = "user_management", indexes = {
        @Index(name = "idx_user_username_unq", columnList = "username", unique = true),
        @Index(name = "idx_user_email_unq", columnList = "email", unique = true),
        @Index(name = "idx_user_phone_unq", columnList = "phone", unique = true)
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class User extends BaseEntity {
    @Column(name = "username", nullable = false, unique = true, length = 50)
    String username;

    @Column(name = "password", nullable = false)
    String password;

    @Column(name = "email", unique = true)
    String email;

    @Column(name = "phone", length = 15, unique = true)
    String phone;

    @Column(name = "active", nullable = false)
    boolean active = true;

    @Column(name = "locked", nullable = false)
    boolean locked = false;

    @Column(name = "mfa_enabled", nullable = false)
    boolean mfaEnabled = false;

    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    UserProfile userProfile;

    @Column(name = "first_login", nullable = false)
    boolean firstLogin = true;

}