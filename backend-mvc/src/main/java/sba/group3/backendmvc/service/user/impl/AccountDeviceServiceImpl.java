package sba.group3.backendmvc.service.user.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.response.user.DeviceSessionResponse;
import sba.group3.backendmvc.mapper.user.DeviceSessionMapper;
import sba.group3.backendmvc.repository.user.DeviceSessionRepository;
import sba.group3.backendmvc.service.auth.AuthTokenBlacklistService;
import sba.group3.backendmvc.service.auth.TokenIssuerService;
import sba.group3.backendmvc.service.infrastructure.CookieService;
import sba.group3.backendmvc.service.user.AccountDeviceService;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AccountDeviceServiceImpl implements AccountDeviceService {
    private final DeviceSessionRepository deviceSessionRepository;
    private final DeviceSessionMapper deviceSessionMapper;
    private final AuthTokenBlacklistService authTokenBlacklistService;
    private final TokenIssuerService tokenIssuerService;
    private final CookieService cookieService;

    @Override
    public List<DeviceSessionResponse> getAllDeviceSessions(UUID userId) {
        return deviceSessionRepository.findAllByUser_Id(userId).stream().map(deviceSessionMapper::toDto1).toList();
    }

    @Transactional
    @Override
    public void logoutAllDeviceSessions(UUID userId) {
        for (var deviceSession : deviceSessionRepository.findAllByUser_Id(userId)) {
            tokenIssuerService.revokeTokens(deviceSession);
            deviceSession.setRevoked(true);
            deviceSession.setTrusted(false);
            deviceSessionRepository.save(deviceSession);
        }
        cookieService.clearRefreshTokenCookie();
    }

    @Transactional
    @Override
    public void logoutDeviceSession(UUID userId, UUID deviceSessionId) {
        var deviceSession = deviceSessionRepository.findByIdAndUser_Id(deviceSessionId, userId)
                .orElseThrow(() -> new IllegalArgumentException("Device session not found"));
        tokenIssuerService.revokeTokens(deviceSession);
        deviceSession.setRevoked(true);
        deviceSession.setTrusted(false);
        deviceSessionRepository.save(deviceSession);
    }

}
