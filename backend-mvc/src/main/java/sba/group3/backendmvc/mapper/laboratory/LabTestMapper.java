package sba.group3.backendmvc.mapper.laboratory;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.laboratory.LabTestRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResponse;
import sba.group3.backendmvc.entity.laboratory.LabTest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface LabTestMapper {
    LabTest toEntity(LabTestRequest labTestRequest);

    LabTestRequest toDto(LabTest labTest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LabTest partialUpdate(LabTestRequest labTestRequest, @MappingTarget LabTest labTest);

    LabTest toEntity(LabTestResponse labTestResponse);

    LabTestResponse toDto1(LabTest labTest);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    LabTest partialUpdate(LabTestResponse labTestResponse, @MappingTarget LabTest labTest);
}