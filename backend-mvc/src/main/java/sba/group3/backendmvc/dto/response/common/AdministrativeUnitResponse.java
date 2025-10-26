package sba.group3.backendmvc.dto.response.common;

import java.io.Serializable;

/**
 * DTO for {@link sba.group3.backendmvc.entity.common.AdministrativeUnit}
 */
public record AdministrativeUnitResponse(String code, String name) implements Serializable {
}