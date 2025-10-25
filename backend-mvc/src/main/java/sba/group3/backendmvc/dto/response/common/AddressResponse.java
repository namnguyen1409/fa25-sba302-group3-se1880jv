package sba.group3.backendmvc.dto.response.common;

import java.io.Serializable;

/**
 * DTO for {@link sba.group3.backendmvc.entity.common.Address}
 */
public record AddressResponse(String street, String wardName, String districtName,
                              String city) implements Serializable {
}