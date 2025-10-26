package sba.group3.backendmvc.service.common.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.response.common.AdministrativeUnitResponse;
import sba.group3.backendmvc.entity.common.AdministrativeUnit;
import sba.group3.backendmvc.enums.AdministrativeUnitLevel;
import sba.group3.backendmvc.mapper.common.AdministrativeUnitMapper;
import sba.group3.backendmvc.repository.common.AdministrativeUnitRepository;
import sba.group3.backendmvc.service.common.AdministrativeUnitService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AdministrativeUnitServiceImpl implements AdministrativeUnitService {


    AdministrativeUnitRepository administrativeUnitRepository;
    AdministrativeUnitMapper administrativeUnitMapper;
    
    @Override
    public List<AdministrativeUnitResponse> getUnit(AdministrativeUnitLevel level, String parentCode) {
        List<AdministrativeUnit> units;
        if (parentCode != null && !parentCode.isEmpty()) {
            units = administrativeUnitRepository.findByLevelAndParentCode(level, parentCode);
        } else {
            units = administrativeUnitRepository.findByLevel(level);
        }
        return units.stream().map(administrativeUnitMapper::toDto).collect(Collectors.toList());
    }

}
