package sba.group3.backendmvc.mapper.pharmacy;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.pharmacy.DispenseRecordRequest;
import sba.group3.backendmvc.dto.response.pharmacy.DispenseRecordResponse;
import sba.group3.backendmvc.entity.pharmacy.DispenseRecord;
import sba.group3.backendmvc.mapper.examination.PrescriptionMapper;
import sba.group3.backendmvc.mapper.staff.StaffMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PrescriptionMapper.class, StaffMapper.class})
public interface DispenseRecordMapper {
    DispenseRecord toEntity(DispenseRecordResponse dispenseRecordResponse);

    DispenseRecordResponse toDto(DispenseRecord dispenseRecord);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DispenseRecord partialUpdate(DispenseRecordResponse dispenseRecordResponse, @MappingTarget DispenseRecord dispenseRecord);

    @Mapping(source = "dispensedById", target = "dispensedBy.id")
    @Mapping(source = "prescriptionId", target = "prescription.id")
    DispenseRecord toEntity(DispenseRecordRequest dispenseRecordRequest);

    @InheritInverseConfiguration(name = "toEntity")
    DispenseRecordRequest toDto1(DispenseRecord dispenseRecord);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    DispenseRecord partialUpdate(DispenseRecordRequest dispenseRecordRequest, @MappingTarget DispenseRecord dispenseRecord);
}