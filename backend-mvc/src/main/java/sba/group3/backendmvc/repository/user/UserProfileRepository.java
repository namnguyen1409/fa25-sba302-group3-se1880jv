package sba.group3.backendmvc.repository.user;

import sba.group3.backendmvc.entity.user.UserProfile;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserProfileRepository extends BaseRepository<UserProfile, UUID> {
    Optional<UserProfile> findByUser_Id(UUID userId);
}