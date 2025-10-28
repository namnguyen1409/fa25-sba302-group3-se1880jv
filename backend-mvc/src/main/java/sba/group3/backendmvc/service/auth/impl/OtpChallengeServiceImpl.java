package sba.group3.backendmvc.service.auth.impl;

import dev.samstevens.totp.code.CodeVerifier;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.entity.auth.OtpChallenge;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.MfaType;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.repository.auth.OtpChallengeRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.OtpChallengeService;
import sba.group3.backendmvc.service.infrastructure.EmailSender;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpChallengeServiceImpl implements OtpChallengeService {

    OtpChallengeRepository otpChallengeRepository;
    EmailSender emailSender;
    CodeVerifier codeVerifier;
    private final UserRepository userRepository;

    @NonFinal
    @Value("${app.frontend.url}")
    String frontendUrl;

    @Transactional
    @Override
    public OtpChallenge create(User user, MfaConfig config) {
        return create(user, config, null);
    }

    @Transactional
    @Override
    public OtpChallenge verify(User user, MfaConfig config, UUID challengeId, String inputCode) {
        OtpChallenge challenge = null;

        switch (config.getMfaType()) {
            case EMAIL, SMS, PUSH_NOTIFICATION -> {
                challenge = otpChallengeRepository.findById(challengeId)
                        .orElseThrow(() -> new IllegalArgumentException("Invalid challenge ID"));

                if (Boolean.TRUE.equals(challenge.getDeleted()) || challenge.getExpiresAt().isBefore(Instant.now())) {
                    throw new IllegalArgumentException("OTP challenge expired or deleted");
                }

                if (challenge.getAttemptCount() >= 5) {
                    throw new IllegalArgumentException("Maximum verification attempts exceeded");
                }

                if (!challenge.getCode().equals(inputCode)) {
                    challenge.setAttemptCount(challenge.getAttemptCount() + 1);
                    otpChallengeRepository.save(challenge);
                    throw new IllegalArgumentException("Invalid OTP code");
                }

                challenge.setVerified(true);
                challenge.setExpiresAt(Instant.now()); // invalidate after success
                otpChallengeRepository.save(challenge);
            }

            case TOTP -> {
                boolean valid = codeVerifier.isValidCode(config.getSecret(), inputCode);
                if (!valid) {
                    throw new IllegalArgumentException("Invalid TOTP code");
                }
                challenge = OtpChallenge.builder()
                        .user(user)
                        .mfaType(MfaType.TOTP)
                        .verified(true)
                        .expiresAt(Instant.now().plus(30, ChronoUnit.SECONDS)) // optional for logging
                        .build();
            }

            default -> throw new IllegalArgumentException("Unsupported MFA type: " + config.getMfaType());
        }

        return challenge;
    }


    @Transactional
    @Override
    public OtpChallenge create(User user, MfaConfig config, Map<String, Object> metadata) {
        otpChallengeRepository.findActiveByUserAndType(user.getId(), config.getMfaType())
                .ifPresent(old -> {
                    old.setDeleted(true);
                    otpChallengeRepository.save(old);
                });
        var otp = String.format("%06d", new SecureRandom().nextInt(1_000_000));

        OtpChallenge challenge = OtpChallenge.builder()
                .user(user)
                .mfaType(config.getMfaType())
                .code(otp)
                .expiresAt(Instant.now().plus(5, ChronoUnit.MINUTES)) // 5 minutes expiry
                .verified(false)
                .attemptCount(0)
                .metadata(metadata)
                .build();

        switch (config.getMfaType()) {
            case EMAIL -> emailSender.sendOtp(config.getContact(), otp);
            case SMS -> log.info("Sending SMS OTP to {}: {}", config.getContact(), otp);
            case TOTP -> {
                // TOTP does not require sending OTP, user generates it via authenticator app
            }
            case PUSH_NOTIFICATION -> {
                // use push notification service to send OTP
            }
            case PASSKEY -> {
                // redirect to passkey flow
            }
            default -> throw new IllegalArgumentException("Invalid MFA Type");
        }

        return otpChallengeRepository.save(challenge);
    }


    @Transactional
    @Override
    public OtpChallenge createEmailVerification(User user, String newEmail) {
        otpChallengeRepository.findActiveByUserAndType(user.getId(), MfaType.EMAIL_VERIFICATION)
                .ifPresent(old -> {
                    old.setDeleted(true);
                    otpChallengeRepository.save(old);
                });

        String token = UUID.randomUUID().toString();

        OtpChallenge challenge = OtpChallenge.builder()
                .user(user)
                .mfaType(MfaType.EMAIL_VERIFICATION)
                .code(token)
                .expiresAt(Instant.now().plus(1, ChronoUnit.HOURS))
                .verified(false)
                .metadata(Map.of(
                        "newEmail", newEmail
                ))
                .attemptCount(0)
                .build();

        otpChallengeRepository.save(challenge);

        String verifyLink = frontendUrl + "/verify-email?token=" + token;
        emailSender.send(
                newEmail,
                "Xác nhận đổi địa chỉ email",
                """
                        <p>Xin chào %s,</p>
                        <p>Vui lòng nhấn vào liên kết dưới đây để xác nhận đổi email:</p>
                        <p><a href="%s">%s</a></p>
                        <p>Liên kết này có hiệu lực trong 1 giờ.</p>
                        """.formatted(user.getUsername(), verifyLink, verifyLink)
        );

        return challenge;
    }


    @Transactional
    @Override
    public void verifyEmailChange(String token) {
        var challenge = otpChallengeRepository.findByCodeAndMfaType(token, MfaType.EMAIL_VERIFICATION)
                .orElseThrow(() -> new IllegalArgumentException("Token không hợp lệ"));

        if (challenge.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalArgumentException("Token đã hết hạn");
        }

        Map<String, Object> meta = challenge.getMetadata();
        String newEmail = (String) meta.get("newEmail");
        if (newEmail == null) {
            throw new AppException(ErrorCode.UNCATEGORIZED);
        }

        if (userRepository.existsByEmail(newEmail)) {
            throw new AppException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        var user = challenge.getUser();
        user.setEmail(newEmail);

        challenge.setVerified(true);
        challenge.setDeleted(true);
        otpChallengeRepository.save(challenge);
    }


    private void verifyOtpChallenge(UUID challengeId, String inputCode) {
        var challenge = otpChallengeRepository.findById(challengeId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid challenge ID"));

        if (Boolean.TRUE.equals(challenge.getDeleted()) || challenge.getExpiresAt().isBefore(Instant.now())) {
            throw new IllegalArgumentException("OTP challenge is expired or deleted");
        }

        if (challenge.getAttemptCount() >= 5) {
            throw new IllegalArgumentException("Maximum verification attempts exceeded");
        }

        if (!challenge.getCode().equals(inputCode)) {
            challenge.setAttemptCount(challenge.getAttemptCount() + 1);
            otpChallengeRepository.save(challenge);
            throw new IllegalArgumentException("Invalid OTP code");
        }

        challenge.setVerified(true);
        challenge.setExpiresAt(Instant.now()); // Invalidate after successful verification
        otpChallengeRepository.save(challenge);
    }

    private void verifyTotpChallenge(MfaConfig config, String inputCode) {
        if (!codeVerifier.isValidCode(config.getSecret(), inputCode))
            throw new IllegalArgumentException("Invalid TOTP code");

    }


}
