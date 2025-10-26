package sba.group3.backendmvc.service.common;

import sba.group3.backendmvc.dto.response.common.AdministrativeUnitResponse;
import sba.group3.backendmvc.enums.AdministrativeUnitLevel;

import java.util.List;

public interface AdministrativeUnitService {
    List<AdministrativeUnitResponse> getUnit(AdministrativeUnitLevel level, String parentCode);
}
