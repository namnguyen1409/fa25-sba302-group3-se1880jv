package sba.group3.backendmvc.service.auth.impl;

import dev.samstevens.totp.code.HashingAlgorithm;
import dev.samstevens.totp.qr.QrData;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.apache.commons.codec.binary.Base32;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.request.auth.TOTPConfirmRequest;
import sba.group3.backendmvc.dto.response.auth.MfaSetupResponse;
import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.enums.MfaType;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.repository.auth.MfaConfigRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.MfaConfigService;
import sba.group3.backendmvc.service.auth.OtpChallengeService;

import java.security.SecureRandom;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MfaConfigServiceImpl implements MfaConfigService {

    OtpChallengeService otpChallengeService;
    MfaConfigRepository mfaConfigRepository;
    UserRepository userRepository;
    @NonFinal
    @Value("${security.jwt.token.issuer}")
    String issuer;

    @Override
    public List<MfaConfig> findAllByUserId(UUID userId) {
        return mfaConfigRepository.findAllByUserId(userId);
    }

    @Override
    public MfaSetupResponse setupTotpMfa(UUID userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );
        boolean alreadyHasTotp = mfaConfigRepository.existsByUserIdAndMfaType(user.getId(), MfaType.TOTP);
        if (alreadyHasTotp) {
            throw new IllegalStateException("Bạn đã bật TOTP trước đó. Vui lòng tắt trước khi thêm mới.");
        }
        var secretBytes = new byte[20];
        new SecureRandom().nextBytes(secretBytes);
        var secret = new Base32().encodeToString(secretBytes);
        var qrUri = new QrData.Builder()
                .label(user.getEmail())
                .secret(secret)
                .issuer(issuer)
                .digits(6)
                .period(30)
                .algorithm(HashingAlgorithm.SHA1)
                .build()
                .getUri();
        return new MfaSetupResponse(secret, qrUri);
    }

    @Transactional
    @Override
    public void confirmTotpMfa(UUID userId, TOTPConfirmRequest request) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );
        var tempConfig = MfaConfig.builder()
                .user(user)
                .mfaType(MfaType.TOTP)
                .secret(request.getSecret())
                .primary(true)
                .revoked(false)
                .build();
        otpChallengeService.verify(user, tempConfig, null, request.getCode());
        mfaConfigRepository.save(tempConfig);
    }

    @Transactional
    @Override
    public boolean verifyTotpMfa(UUID userId, String code) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AppException(ErrorCode.USER_NOT_FOUND)
        );
        var totpConfig = mfaConfigRepository.findByUserIdAndMfaTypeAndRevokedFalse(user.getId(), MfaType.TOTP)
                .orElseThrow(() -> new AppException(ErrorCode.MFA_CONFIG_NOT_FOUND));
        try {
            otpChallengeService.verify(user, totpConfig, null, code);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }


}
