package sba.group3.backendmvc.service.user.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.entity.user.DeviceSession;
import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.repository.user.DeviceSessionRepository;
import sba.group3.backendmvc.service.user.DeviceSessionService;


@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class DeviceSessionServiceImpl implements DeviceSessionService {


    private final DeviceSessionRepository deviceSessionRepository;

    @Transactional
    @Override
    public DeviceSession ensureDeviceSession(User user, String deviceId, boolean rememberMe, boolean trusted) {
        var session = deviceSessionRepository.findByUserIdAndDeviceId(user.getId(), deviceId)
                .orElseGet(() -> DeviceSession.builder()
                        .user(user)
                        .deviceId(deviceId)
                        .build());
        session.setTrusted(trusted);
        session.setRememberMe(rememberMe);
        return deviceSessionRepository.save(session);
    }

}
