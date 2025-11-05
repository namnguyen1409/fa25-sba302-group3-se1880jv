package sba.group3.backendmvc.dto.request.common;

import java.io.Serializable;

/**
 * DTO for {@link sba.group3.backendmvc.entity.common.IcdCode}
 */
public record IcdCodeRequest(String code, String name, String description, String chapter,
                             String icdVersion) implements Serializable {
}