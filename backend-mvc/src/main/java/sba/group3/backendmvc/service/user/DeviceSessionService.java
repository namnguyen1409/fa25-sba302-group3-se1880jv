package sba.group3.backendmvc.service.user;

import sba.group3.backendmvc.entity.user.DeviceSession;
import sba.group3.backendmvc.entity.user.User;

public interface DeviceSessionService {
    DeviceSession ensureDeviceSession(User user, String deviceId, boolean rememberMe, boolean trusted);
}
