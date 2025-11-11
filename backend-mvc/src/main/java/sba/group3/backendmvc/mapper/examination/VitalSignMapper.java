package sba.group3.backendmvc.mapper.examination;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.examination.VitalSignRequest;
import sba.group3.backendmvc.dto.response.examination.VitalSignResponse;
import sba.group3.backendmvc.entity.examination.VitalSign;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VitalSignMapper {
    @Mapping(source = "examinationId", target = "examination.id")
    VitalSign toEntity(VitalSignRequest vitalSignRequest);

    @Mapping(source = "examination.id", target = "examinationId")
    VitalSignRequest toDto(VitalSign vitalSign);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "examinationId", target = "examination.id")
    VitalSign partialUpdate(VitalSignRequest vitalSignRequest, @MappingTarget VitalSign vitalSign);

    VitalSign toEntity(VitalSignResponse vitalSignResponse);

    VitalSignResponse toDto1(VitalSign vitalSign);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VitalSign partialUpdate(VitalSignResponse vitalSignResponse, @MappingTarget VitalSign vitalSign);
}