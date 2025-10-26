package sba.group3.backendmvc.dto.response.user;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link sba.group3.backendmvc.entity.user.Role}
 */
public record RoleNameResponse(String name, Set<PermissionNameResponse> permissions) implements Serializable {
}