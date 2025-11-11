package sba.group3.backendmvc.entity.user;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.auth.OAuthAccount;
import sba.group3.backendmvc.entity.patient.Patient;
import sba.group3.backendmvc.entity.staff.Staff;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
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

    @Builder.Default
    @Column(name = "active", nullable = false)
    boolean active = true;

    @Builder.Default
    @Column(name = "locked", nullable = false)
    boolean locked = false;

    @Builder.Default
    @Column(name = "mfa_enabled", nullable = false)
    boolean mfaEnabled = false;

    @OneToOne(mappedBy = "user", orphanRemoval = true, cascade = CascadeType.ALL)
    UserProfile userProfile;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Staff staff;

    @OneToOne(mappedBy = "user", orphanRemoval = true)
    private Patient patient;

    @Builder.Default
    @Column(name = "first_login", nullable = false)
    boolean firstLogin = true;

    @Builder.Default
    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "roles_id"))
    private Set<Role> roles = new LinkedHashSet<>();

    @Builder.Default
    @OneToMany(mappedBy = "user", orphanRemoval = true, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<OAuthAccount> OAuthAccounts = new LinkedHashSet<>();

}