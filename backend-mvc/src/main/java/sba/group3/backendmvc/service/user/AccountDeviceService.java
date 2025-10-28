package sba.group3.backendmvc.service.user;

import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.response.user.DeviceSessionResponse;

import java.util.List;
import java.util.UUID;

public interface AccountDeviceService {
    List<DeviceSessionResponse> getAllDeviceSessions(UUID userId);

    void logoutAllDeviceSessions(UUID userId);

    @Transactional
    void logoutDeviceSession(UUID userId, UUID deviceSessionId);
}
