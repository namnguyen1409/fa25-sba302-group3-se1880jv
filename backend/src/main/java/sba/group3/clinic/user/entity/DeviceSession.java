package sba.group3.clinic.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import sba.group3.clinic.common.entity.BaseEntity;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "device_session",
        schema = "user_management",
        indexes = {
        @Index(name = "idx_devicesession_user_id", columnList = "user_id"),
        @Index(name = "idx_devicesession_device_id", columnList = "device_id")
}, uniqueConstraints = {
        @UniqueConstraint(name = "uc_devicesession_user_id_device_id", columnNames = {"user_id", "device_id"})
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class DeviceSession extends BaseEntity {
    /**
     * Liên kết đến người dùng sở hữu phiên đăng nhập này.
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    User user;

    /**
     * DeviceID được tạo bởi ứng dụng client để nhận diện thiết bị.
     * <ul>
     *  <li>Với web app, có thể sử dụng UUID lưu trong cookie hoặc localStorage.</li>
     *  <li>Với android, sử dụng uuid lưu trong encrypted shared preferences.</li>
     *  <li>Với iOS, sử dụng uuid lưu trong keychain.</li>
     * </ul>
     */
    @Column(name = "device_id", nullable = false, length = 100)
    String deviceId;

    /**
     * Tên thiết bị đã đăng nhập
     */
    @Column(name = "device_name")
    String deviceName;

    /**
     * Địa chỉ IP của thiết bị khi đăng nhập.
     */
    @Column(name = "ip_address", length = 45)
    String ipAddress;

    /**
     * User-Agent của trình duyệt hoặc ứng dụng client.
     * Giúp nhận diện loại thiết bị, hệ điều hành và trình duyệt.
     */
    @Column(name = "user_agent")
    String userAgent;

    /**
     * Trường trusted hỗ trợ đánh dấu thiết bị đáng tin cậy.
     * </br>
     * Khi người dùng đăng nhập từ một thiết bị mới, hệ thống có thể yêu cầu xác thực đa yếu tố (MFA).
     * Nếu người dùng đánh dấu thiết bị đó là "trusted", các lần đăng nhập tiếp theo từ thiết bị này sẽ không yêu cầu MFA.
     * </br>
     * Mặc định là false.
     */
    @Column(name = "trusted", nullable = false)
    boolean trusted = false;

    /**
     * JTI (JWT ID) của access token và refresh token tương ứng với phiên đăng nhập này.
     * </br>
     * Giúp theo dõi và quản lý các token đã phát hành cho phiên đăng nhập.
     * </br>
     * Ví dụ: khi người dùng đăng xuất hoặc quản trị viên thu hồi phiên đăng nhập,
     * hệ thống có thể sử dụng JTI để xác định và thu hồi các token liên quan.
     */
    @Column(name = "access_token_id")
    String accessTokenId;

    /**
     * JTI (JWT ID) của refresh token tương ứng với phiên đăng nhập này.
     * </br>
     * Giúp theo dõi và quản lý các token đã phát hành cho phiên đăng nhập.
     * </br>
     * Ví dụ: khi người dùng đăng xuất hoặc quản trị viên thu hồi phiên đăng nhập,
     * hệ thống có thể sử dụng JTI để xác định và thu hồi các token liên quan.
     */
    @Column(name = "refresh_token_id")
    String refreshTokenId;

    /**
     *  Lần refresh token cuối cùng
     *  Hỗ trợ tính thời gian sống còn lại của access token và refresh token.
     */
    @Column(name = "last_refreshed_at")
    Instant lastRefreshedAt;

    /**
     * Thời gian sống còn lại của phiên đăng nhập.
     */
    @Column(name = "expires_in")
    Instant expiresIn;

    /**
     * Trường revoked hỗ trợ thu hồi phiên đăng nhập.
     * </br>
     * Khi người dùng đăng xuất hoặc quản trị viên thu hồi phiên đăng nhập, trường này được đặt thành true.
     * Hệ thống sẽ không chấp nhận các yêu cầu từ phiên đăng nhập đã bị thu hồi.
     * </br>
     * Mặc định là false.
     */
    @Column(name = "revoked", nullable = false)
    boolean revoked = false;

    /**
     * RememberMe là tùy chọn để duy trì phiên đăng nhập lâu hơn bình thường.
     * </br>
     * Nếu người dùng chọn "Remember Me" khi đăng nhập, expiresIn sẽ được đặt lại sau mỗi lần refresh token.
     * Nếu không chọn, expiresIn sẽ không thay đổi và phiên đăng nhập sẽ hết hạn theo thời gian ban đầu.
     * </br>
     * Mặc định là false.
     */
    @Column(name = "remember_me", nullable = false)
    boolean rememberMe = false;


}