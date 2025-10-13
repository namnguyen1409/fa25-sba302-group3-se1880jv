package sba.group3.clinic.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.user.entity.User;

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
public class MfaBackupCode extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "code_hash", nullable = false)
    private String codeHash;

    @Column(name = "used", nullable = false)
    Boolean used = false;

}