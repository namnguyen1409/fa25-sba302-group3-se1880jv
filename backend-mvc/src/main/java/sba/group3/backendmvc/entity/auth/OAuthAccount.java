package sba.group3.backendmvc.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.OAuthProvider;

@Getter
@Setter
@Entity
@Table(name = "oauth_account", schema = "authentication", indexes = {
        @Index(name = "idx_oauthaccount_user_id", columnList = "user_id"),
        @Index(name = "idx_oauthaccount_provider_user_id", columnList = "provider_user_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_oauthaccount_provider_user_id", columnNames = {"provider", "provider_user_id"})
})
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
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