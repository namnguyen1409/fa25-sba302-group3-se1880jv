package sba.group3.backendmvc.repository.user;

import sba.group3.backendmvc.entity.user.DeviceSession;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface DeviceSessionRepository extends BaseRepository<DeviceSession, UUID> {
    Optional<DeviceSession> findByUserIdAndDeviceId(UUID userId, String deviceId);
}