package sba.group3.backendmvc.dto.response.organization;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.organization.Department}
 */
public record DepartmentSimpleResponse(UUID id, String name, String description) implements Serializable {
  }