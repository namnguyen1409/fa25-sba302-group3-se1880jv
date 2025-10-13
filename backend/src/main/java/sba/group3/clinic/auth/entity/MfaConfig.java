package sba.group3.clinic.auth.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.common.enums.MfaType;
import sba.group3.clinic.user.entity.User;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "mfa_config")
public class MfaConfig extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Loại phương thức xác thực đa yếu tố ({@link MfaType}) được sử dụng.
     *  <ul>
     *      <li>{@link MfaType#TOTP} - Mã xác thực dựa trên thời gian (Time-based One-Time Password)</li>
     *      <li>{@link MfaType#SMS} - Xác thực qua tin nhắn SMS</li>
     *      <li>{@link MfaType#EMAIL} - Xác thực qua email</li>
     *      <li>{@link MfaType#PUSH_NOTIFICATION} - Xác thực qua thông báo đẩy (Push Notification)</li>
     *      <li>{@link MfaType#PASSKEY} - Xác thực qua khóa bảo mật (Passkey)</li>
     *  </ul>
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "mfa_type", nullable = false, length = 32)
    private MfaType mfaType;

    /**
     * Trường này dùng cho TOTP (Time-based One-Time Password)
     * hoặc các phương thức MFA khác cần lưu trữ bí mật .
     */
    @Column(name = "secret")
    private String secret;

    /**
     * Trường này dùng để lưu thông tin liên hệ như email hoặc số điện thoại
     * tùy thuộc vào loại MFA (mfaType).
     */
    @Column(name = "contact")
    private String contact;

    /**
     * Chỉ định cấu hình MFA chính (primary) hay phụ (secondary).
     * Cấu hình chính sẽ được ưu tiên sử dụng khi xác thực.
     */
    @Column(name = "primary", nullable = false)
    private Boolean primary = false;

    /**
     * Lưu thời điểm cấu hình MFA được xác thực thành công lần cuối.
     * Dùng để theo dõi trạng thái xác thực của cấu hình MFA.
     */
    @Column(name = "last_verified_at")
    private Instant lastVerifiedAt;

}