package sba.group3.backendmvc.service.auth;

import sba.group3.backendmvc.dto.response.auth.AuthResponse;
import sba.group3.backendmvc.entity.user.DeviceSession;
import sba.group3.backendmvc.entity.user.User;

public interface TokenIssuerService {
    AuthResponse issue(User user, DeviceSession deviceSession, String deviceId);
    AuthResponse refresh(User user, DeviceSession deviceSession, String deviceId);

    void revokeTokens(DeviceSession deviceSession);
}
