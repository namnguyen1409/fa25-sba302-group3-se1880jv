package sba.group3.backendmvc.repository.common;

import sba.group3.backendmvc.entity.common.AdministrativeUnit;
import sba.group3.backendmvc.enums.AdministrativeUnitLevel;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface AdministrativeUnitRepository extends BaseRepository<AdministrativeUnit, UUID> {
    List<AdministrativeUnit> findByLevelAndParentCode(AdministrativeUnitLevel level, String parentCode);

    List<AdministrativeUnit> findByLevel(AdministrativeUnitLevel level);
}