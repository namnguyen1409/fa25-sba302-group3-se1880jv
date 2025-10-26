package sba.group3.backendmvc.dto.response.user;

import java.io.Serializable;

/**
 * DTO for {@link sba.group3.backendmvc.entity.user.Permission}
 */
public record PermissionNameResponse(String name) implements Serializable {
}