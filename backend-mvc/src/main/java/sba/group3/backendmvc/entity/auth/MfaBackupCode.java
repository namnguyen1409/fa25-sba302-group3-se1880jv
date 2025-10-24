package sba.group3.backendmvc.entity.auth;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.user.User;

@Getter
@Setter
@Entity
@Table(
        name = "mfa_backup_code",
        schema = "authentication",
        indexes = {
                @Index(name = "idx_mfa_backup_code_user_id", columnList = "user_id")
        }
)
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class MfaBackupCode extends BaseEntity {
    @Column(name = "used", nullable = false)
    Boolean used = false;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "code_hash", nullable = false)
    private String codeHash;

}