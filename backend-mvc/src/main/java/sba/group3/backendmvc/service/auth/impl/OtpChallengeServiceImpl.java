package sba.group3.backendmvc.service.auth.impl;

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

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OtpChallengeServiceImpl implements OtpChallengeService {

    private final OtpChallengeRepository otpChallengeRepository;
    private final EmailSender emailSender;

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
                .expiresAt(Instant.now().plus(5, ChronoUnit.MILLIS)) // 5 minutes expiry
                .verified(false)
                .build();

        switch (config.getMfaType()) {
            case EMAIL -> emailSender.sendOtp(config.getContact(), otp);
            case SMS -> log.info("Sending SMS OTP to {}: {}", config.getContact(), otp);
            case TOTP -> {
            }
            case PUSH_NOTIFICATION -> {
            }
            case PASSKEY -> {
            }
            default -> throw new IllegalArgumentException("Invalid MFA Type");
        }

        return otpChallengeRepository.save(challenge);
    }


}
