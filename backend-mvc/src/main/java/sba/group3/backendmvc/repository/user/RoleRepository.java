package sba.group3.backendmvc.repository.user;

import sba.group3.backendmvc.entity.user.Role;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface RoleRepository extends BaseRepository<Role, UUID> {
    Role findByName(String name);

    List<Role> findByIsDefault(Boolean isDefault);
}