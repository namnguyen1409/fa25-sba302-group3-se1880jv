package sba.group3.backendmvc.service.auth.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.entity.auth.MfaBackupCode;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.repository.auth.MfaBackupCodeRepository;
import sba.group3.backendmvc.repository.user.UserRepository;
import sba.group3.backendmvc.service.auth.MfaBackupCodeService;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MfaBackupCodeServiceImpl implements MfaBackupCodeService {

    private final MfaBackupCodeRepository mfaBackupCodeRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    @Transactional
    @Override
    public List<String> generateBackupCodes(UUID userId) {
        mfaBackupCodeRepository.deleteAll(mfaBackupCodeRepository.findAllByUserId(userId));

        List<String> codes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            String code = UUID.randomUUID().toString().replace("-", "").substring(0, 10);
            String hash = passwordEncoder.encode(code);
            MfaBackupCode entity = MfaBackupCode.builder()
                    .user(userRepository.getReferenceById(userId))
                    .codeHash(hash)
                    .build();
            mfaBackupCodeRepository.save(entity);
            codes.add(code);
        }
        return codes;
    }

    @Transactional
    @Override
    public boolean verifyBackupCode(User user, String rawCode) {
        List<MfaBackupCode> codes = mfaBackupCodeRepository.findAllByUserId(user.getId());
        for (MfaBackupCode backup : codes) {
            if (!backup.isUsed() && passwordEncoder.matches(rawCode, backup.getCodeHash())) {
                backup.setUsed(true);
                mfaBackupCodeRepository.save(backup);
                return true; // success
            }
        }
        return false; // not matched or already used
    }


}
