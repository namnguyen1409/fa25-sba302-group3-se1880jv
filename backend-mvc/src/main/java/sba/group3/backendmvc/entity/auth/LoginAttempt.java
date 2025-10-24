package sba.group3.backendmvc.entity.auth;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.LoginStatus;

@Getter
@Setter
@Entity
@Table(name = "login_attempt",
        schema = "authentication",
        indexes = {
                @Index(name = "idx_loginattempt_user_id", columnList = "user_id"),
                @Index(name = "idx_loginattempt_ip_address", columnList = "ip_address")
        })
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class LoginAttempt extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    @Column(name = "ip_address", length = 45)
    String ipAddress;

    @Column(name = "user_agent")
    String userAgent;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    LoginStatus status;

}