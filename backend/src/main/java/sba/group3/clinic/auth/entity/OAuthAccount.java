package sba.group3.clinic.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.common.enums.OAuthProvider;
import sba.group3.clinic.user.entity.User;

@Getter
@Setter
@Entity
@Table(name = "oauth_account", schema = "authentication", indexes = {
        @Index(name = "idx_oauthaccount_user_id", columnList = "user_id"),
        @Index(name = "idx_oauthaccount_provider_user_id", columnList = "provider_user_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_oauthaccount_provider_user_id", columnNames = {"provider", "provider_user_id"})
})
public class OAuthAccount extends BaseEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false, length = 20)
    private OAuthProvider provider;

    @Column(name = "provider_user_id", nullable = false)
    private String providerUserId;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "avatar_url", length = 500)
    private String avatarUrl;

}