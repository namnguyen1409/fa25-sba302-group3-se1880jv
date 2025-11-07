package sba.group3.backendmvc.mapper.examination;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.examination.DiagnosisRequest;
import sba.group3.backendmvc.dto.response.examination.DiagnosisResponse;
import sba.group3.backendmvc.entity.examination.Diagnosis;
import sba.group3.backendmvc.mapper.common.IcdCodeMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {IcdCodeMapper.class})
public interface DiagnosisMapper {
    Diagnosis toEntity(DiagnosisResponse diagnosisResponse);

    DiagnosisResponse toDto(Diagnosis diagnosis);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Diagnosis partialUpdate(DiagnosisResponse diagnosisResponse, @MappingTarget Diagnosis diagnosis);

    @Mapping(source = "icdCodeId", target = "icdCode.id")
    @Mapping(source = "examinationId", target = "examination.id")
    Diagnosis toEntity(DiagnosisRequest diagnosisRequest);

    @Mapping(source = "icdCode.id", target = "icdCodeId")
    @Mapping(source = "examination.id", target = "examinationId")
    DiagnosisRequest toDto1(Diagnosis diagnosis);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "examinationId", target = "examination.id")
    Diagnosis partialUpdate(DiagnosisRequest diagnosisRequest, @MappingTarget Diagnosis diagnosis);
}