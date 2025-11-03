package sba.group3.backendmvc.mapper.examination;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.examination.PrescriptionItemRequest;
import sba.group3.backendmvc.dto.response.examination.PrescriptionItemResponse;
import sba.group3.backendmvc.entity.examination.PrescriptionItem;
import sba.group3.backendmvc.mapper.pharmacy.MedicineMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {MedicineMapper.class})
public interface PrescriptionItemMapper {
    @Mapping(source = "medicineId", target = "medicine.id")
    @Mapping(source = "prescriptionId", target = "prescription.id")
    PrescriptionItem toEntity(PrescriptionItemRequest prescriptionItemRequest);

    @InheritInverseConfiguration(name = "toEntity")
    PrescriptionItemRequest toDto(PrescriptionItem prescriptionItem);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PrescriptionItem partialUpdate(PrescriptionItemRequest prescriptionItemRequest, @MappingTarget PrescriptionItem prescriptionItem);

    PrescriptionItem toEntity(PrescriptionItemResponse prescriptionItemResponse);

    PrescriptionItemResponse toDto1(PrescriptionItem prescriptionItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    PrescriptionItem partialUpdate(PrescriptionItemResponse prescriptionItemResponse, @MappingTarget PrescriptionItem prescriptionItem);
}