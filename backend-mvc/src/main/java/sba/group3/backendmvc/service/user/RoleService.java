package sba.group3.backendmvc.service.user;

import sba.group3.backendmvc.entity.user.Role;

import java.util.List;
import java.util.Set;

public interface RoleService {
    Role findByName(String name);

    List<Role> findDefaultRoles();
}
