package sba.group3.backendmvc.service.user.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.controller.user.AccountSecurityController;
import sba.group3.backendmvc.dto.response.auth.MfaConfigResponse;
import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.enums.MfaType;
import sba.group3.backendmvc.mapper.auth.MfaConfigMapper;
import sba.group3.backendmvc.repository.auth.MfaBackupCodeRepository;
import sba.group3.backendmvc.repository.auth.MfaConfigRepository;
import sba.group3.backendmvc.repository.auth.OtpChallengeRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.MfaBackupCodeService;
import sba.group3.backendmvc.service.auth.MfaConfigService;
import sba.group3.backendmvc.service.auth.OtpChallengeService;
import sba.group3.backendmvc.service.infrastructure.EmailSender;
import sba.group3.backendmvc.service.user.AccountSecurityService;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountSecurityServiceImpl implements AccountSecurityService {

    MfaConfigRepository mfaConfigRepository;
    MfaConfigMapper mfaConfigMapper;
    UserRepository userRepository;
    OtpChallengeService otpChallengeService;
    OtpChallengeRepository otpChallengeRepository;
    MfaBackupCodeService mfaBackupCodeService;
    PasswordEncoder passwordEncoder;
    MfaConfigService mfaConfigService;
    EmailSender emailSender;
    MfaBackupCodeRepository mfaBackupCodeRepository;

    @Transactional
    @Override
    public void changePassword(UUID userId, AccountSecurityController.ChangePasswordRequest request) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!passwordEncoder.matches(request.currentPassword(), user.getPassword())) {
            throw new SecurityException("Mật khẩu hiện tại không đúng.");
        }
        if (passwordEncoder.matches(request.newPassword(), user.getPassword())) {
            throw new IllegalArgumentException("Mật khẩu mới không được trùng mật khẩu cũ.");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

        emailSender.sendSecurityAlert(user.getEmail(), "Your password was changed successfully.");
    }


    @Transactional
    @Override
    public void enableMfa(UUID userId) {
        if (mfaConfigRepository.countByUser_Id(userId) == 0)
            throw new IllegalStateException("Cần có ít nhất một phương thức MFA để bật.");

        var user = userRepository.getReferenceById(userId);
        user.setMfaEnabled(true);
        userRepository.save(user);
    }

    @Transactional
    @Override
    public void disableMfa(UUID userId, AccountSecurityController.MfaDisableRequest request) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!verifyIdentity(userId, request.verificationMethod(), request.code()))
            throw new SecurityException("Xác minh thất bại. Không thể tắt MFA.");

        user.setMfaEnabled(false);
        userRepository.save(user);

        mfaConfigRepository.deleteAllByUser_Id(userId);
        mfaBackupCodeRepository.deleteAllByUser_Id(userId);

        emailSender.sendSecurityAlert(user.getEmail(), "Your MFA has been disabled.");
    }

    // ==========================
    // ⚙️ MFA Config management
    // ==========================

    @Transactional
    @Override
    public void deleteMfaConfig(UUID userId, AccountSecurityController.MfaDeleteRequest request) {
        var config = mfaConfigRepository.findById(request.configId())
                .orElseThrow(() -> new IllegalArgumentException("MFA Config not found"));

        if (!config.getUser().getId().equals(userId))
            throw new SecurityException("MFA Config không thuộc về user này");

        if (!verifyIdentity(userId, request.verificationMethod(), request.code()))
            throw new SecurityException("Xác minh thất bại. Không thể xóa MFA Config.");

        // Nếu là config primary -> disable toàn bộ MFA
        if (Boolean.TRUE.equals(config.getPrimary())) {
            var user = config.getUser();
            user.setMfaEnabled(false);
            userRepository.save(user);
        }

        mfaConfigRepository.delete(config);
        emailSender.sendSecurityAlert(config.getUser().getEmail(),
                "Your MFA method (" + config.getMfaType() + ") has been removed.");
    }

    @Override
    public List<MfaConfigResponse> getMfaConfigsByUserId(UUID userId) {
        return mfaConfigRepository.findAllByUserId(userId)
                .stream()
                .map(mfaConfigMapper::toDto)
                .toList();
    }

    // ==========================
    // 📧 Email MFA setup
    // ==========================

    @Override
    public AccountSecurityController.MfaInitResponse initEmailMfa(UUID userId, String contact) {
        var user = userRepository.getReferenceById(userId);

        if (mfaConfigRepository.existsByUserIdAndMfaTypeAndContact(userId, MfaType.EMAIL, contact))
            throw new IllegalStateException("Bạn đã bật MFA Email với địa chỉ này.");

        var config = MfaConfig.builder()
                .user(user)
                .mfaType(MfaType.EMAIL)
                .contact(contact)
                .build();

        var otpChallenge = otpChallengeService.create(user, config, Map.of("email", contact));

        return new AccountSecurityController.MfaInitResponse(otpChallenge.getId());
    }

    @Transactional
    @Override
    public MfaConfigResponse confirmEmailMfa(UUID userId, AccountSecurityController.MfaConfirmRequest request) {
        var user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var challenge = otpChallengeRepository.findById(request.challengeId())
                .orElseThrow(() -> new IllegalArgumentException("Challenge not found"));

        if (!challenge.getUser().getId().equals(userId))
            throw new SecurityException("Challenge không thuộc về user này");

        if (challenge.getExpiresAt().isBefore(Instant.now()))
            throw new IllegalArgumentException("Mã xác thực đã hết hạn");

        if (!challenge.getCode().equals(request.code()))
            throw new IllegalArgumentException("Mã xác thực không đúng");

        challenge.setVerified(true);
        otpChallengeRepository.save(challenge);

        var mfaConfig = MfaConfig.builder()
                .user(user)
                .mfaType(MfaType.EMAIL)
                .contact((String) challenge.getMetadata().get("email"))
                .primary(true)
                .build();

        return mfaConfigMapper.toDto(mfaConfigRepository.save(mfaConfig));
    }

    @Transactional
    @Override
    public List<String> generateBackupCodes(UUID userId) {
        return mfaBackupCodeService.generateBackupCodes(userId);
    }

    @Override
    public void firstLogin(UUID uuid, AccountSecurityController.FirstLoginRequest request) {
        var user = userRepository.findById(uuid)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        if (!user.isFirstLogin())
            throw new IllegalStateException("Not the first login.");
        if (!Objects.equals(user.getUsername(), request.username()) && userRepository.existsByUsername(request.username()))
            throw new IllegalArgumentException("Username already taken.");

        user.setFirstLogin(false);
        user.setUsername(request.username());
        user.setPassword(passwordEncoder.encode(request.newPassword()));
        userRepository.save(user);

    }

    // ==========================
    // 🔍 Common helper
    // ==========================

    private boolean verifyIdentity(UUID userId, String method, String code) {
        var user = userRepository.getReferenceById(userId);
        return switch (method.toUpperCase()) {
            case "PASSWORD" -> passwordEncoder.matches(code, user.getPassword());
            case "TOTP" -> mfaConfigService.verifyTotpMfa(userId, code);
            case "BACKUP_CODE" -> mfaBackupCodeService.verifyBackupCode(user, code);
            default -> false;
        };
    }
}
