package sba.group3.backendmvc.entity.auth;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.proxy.HibernateProxy;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.OAuthProvider;

import java.util.Objects;

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

    @Builder.Default
    @Column(name = "is_revoke")
    private Boolean isRevoke = false;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        OAuthAccount that = (OAuthAccount) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}