package sba.group3.clinic.auth.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.common.enums.LoginStatus;
import sba.group3.clinic.user.entity.User;

import java.time.Instant;

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