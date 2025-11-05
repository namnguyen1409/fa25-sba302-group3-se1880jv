package sba.group3.backendmvc.mapper.pharmacy;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.pharmacy.MedicineRequest;
import sba.group3.backendmvc.dto.response.pharmacy.MedicineResponse;
import sba.group3.backendmvc.entity.pharmacy.Medicine;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicineMapper {
    Medicine toEntity(MedicineRequest medicineRequest);

    MedicineRequest toDto(Medicine medicine);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Medicine partialUpdate(MedicineRequest medicineRequest, @MappingTarget Medicine medicine);

    Medicine toEntity(MedicineResponse medicineResponse);

    MedicineResponse toDto1(Medicine medicine);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Medicine partialUpdate(MedicineResponse medicineResponse, @MappingTarget Medicine medicine);
}