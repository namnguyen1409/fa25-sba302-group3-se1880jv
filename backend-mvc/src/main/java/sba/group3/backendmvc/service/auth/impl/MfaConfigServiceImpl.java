package sba.group3.backendmvc.service.auth.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.entity.auth.MfaConfig;
import sba.group3.backendmvc.repository.auth.MfaConfigRepository;
import sba.group3.backendmvc.service.auth.MfaConfigService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class MfaConfigServiceImpl implements MfaConfigService {


    MfaConfigRepository mfaConfigRepository;

    @Cacheable(value = "mfaConfigs", key = "#userId")
    @Override
    public List<MfaConfig> findAllByUserId(UUID userId) {
        return mfaConfigRepository.findAllByUserId(userId);
    }


}
