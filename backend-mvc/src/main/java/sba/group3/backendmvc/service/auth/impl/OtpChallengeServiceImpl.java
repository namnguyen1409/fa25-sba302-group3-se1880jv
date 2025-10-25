package sba.group3.backendmvc.service.auth.impl;

import dev.samstevens.totp.code.CodeVerifier;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.entity.auth.OtpChallenge;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.repository.auth.OtpChallengeRepository;
import sba.group3.backendmvc.service.auth.OtpChallengeService;
import sba.group3.backendmvc.service.infrastructure.EmailSender;

import java.security.SecureRandom;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpChallengeServiceImpl implements OtpChallengeService {

    OtpChallengeRepository otpChallengeRepository;
    EmailSender emailSender;
    CodeVerifier codeVerifier;

    @Transactional
    @Override
    public OtpChallenge create(User user, MfaConfig config) {
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

    @Override
    public void verify(User user, MfaConfig config, UUID challengeId, String inputCode) {
        switch (config.getMfaType()) {
            case EMAIL, SMS, PUSH_NOTIFICATION -> verifyOtpChallenge(challengeId, inputCode);
            case TOTP -> verifyTotpChallenge(config, inputCode);
            default -> throw new IllegalArgumentException("Invalid MFA Type");
        }
        // after success -> delete or invalidate the challenge
        otpChallengeRepository.deleteById(challengeId);
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
