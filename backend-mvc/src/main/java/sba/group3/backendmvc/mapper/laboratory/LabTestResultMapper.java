package sba.group3.backendmvc.mapper.laboratory;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.laboratory.LabTestResultRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResultResponse;
import sba.group3.backendmvc.entity.laboratory.LabTestResult;
import sba.group3.backendmvc.mapper.staff.StaffMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {LabTestMapper.class, StaffMapper.class})
public interface LabTestResultMapper {
    @Mapping(source = "verifiedById", target = "verifiedBy.id")
    @Mapping(source = "labTestId", target = "labTest.id")
    @Mapping(source = "labOrderId", target = "labOrder.id")
    LabTestResult toEntity(LabTestResultRequest labTestResultRequest);

    @InheritInverseConfiguration(name = "toEntity")
    LabTestResultRequest toDto(LabTestResult labTestResult);


    @Mapping(target = "labOrder", ignore = true)
    @Mapping(target = "verifiedBy",  ignore = true)
    @Mapping(target = "labTest", ignore = true)
    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LabTestResult partialUpdate(LabTestResultRequest labTestResultRequest, @MappingTarget LabTestResult labTestResult);

    LabTestResult toEntity(LabTestResultResponse labTestResultResponse);

    LabTestResultResponse toDto1(LabTestResult labTestResult);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LabTestResult partialUpdate(LabTestResultResponse labTestResultResponse, @MappingTarget LabTestResult labTestResult);
}