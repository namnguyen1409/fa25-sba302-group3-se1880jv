package sba.group3.backendmvc.repository.user;

import sba.group3.backendmvc.entity.user.User;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends BaseRepository<User, UUID> {
    Optional<User> findByUsername(String username);
}