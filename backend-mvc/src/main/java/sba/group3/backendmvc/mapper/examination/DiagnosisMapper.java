package sba.group3.backendmvc.mapper.examination;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.examination.DiagnosisResponse;
import sba.group3.backendmvc.entity.examination.Diagnosis;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface DiagnosisMapper {
    Diagnosis toEntity(DiagnosisResponse diagnosisResponse);

    DiagnosisResponse toDto(Diagnosis diagnosis);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Diagnosis partialUpdate(DiagnosisResponse diagnosisResponse, @MappingTarget Diagnosis diagnosis);
}