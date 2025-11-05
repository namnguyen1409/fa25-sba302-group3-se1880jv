package sba.group3.backendmvc.dto.response.common;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.common.IcdCode}
 */
public record IcdCodeResponse(UUID id, String code, String name, String description, String chapter,
                              String icdVersion) implements Serializable {
}