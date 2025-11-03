package sba.group3.backendmvc.mapper.laboratory;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.laboratory.LabOrderRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabOrderResponse;
import sba.group3.backendmvc.entity.laboratory.LabOrder;
import sba.group3.backendmvc.mapper.patient.PatientMapper;
import sba.group3.backendmvc.mapper.staff.StaffMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PatientMapper.class, StaffMapper.class, LabTestResultMapper.class})
public interface LabOrderMapper {
    @Mapping(source = "examinationId", target = "examination.id")
    @Mapping(source = "requestedById", target = "requestedBy.id")
    @Mapping(source = "patientId", target = "patient.id")
    LabOrder toEntity(LabOrderRequest labOrderRequest);

    @InheritInverseConfiguration(name = "toEntity")
    LabOrderRequest toDto(LabOrder labOrder);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LabOrder partialUpdate(LabOrderRequest labOrderRequest, @MappingTarget LabOrder labOrder);

    @Mapping(source = "examinationId", target = "examination.id")
    LabOrder toEntity(LabOrderResponse labOrderResponse);

    @AfterMapping
    default void linkResults(@MappingTarget LabOrder labOrder) {
        labOrder.getResults().forEach(result -> result.setLabOrder(labOrder));
    }

    @Mapping(source = "examination.id", target = "examinationId")
    LabOrderResponse toDto1(LabOrder labOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "examinationId", target = "examination.id")
    LabOrder partialUpdate(LabOrderResponse labOrderResponse, @MappingTarget LabOrder labOrder);
}