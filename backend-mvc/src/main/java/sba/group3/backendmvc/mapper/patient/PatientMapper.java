package sba.group3.backendmvc.mapper.patient;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.patient.PatientRequest;
import sba.group3.backendmvc.dto.response.patient.PatientResponse;
import sba.group3.backendmvc.entity.patient.Patient;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {
    Patient toEntity(PatientResponse patientResponse);

    PatientResponse toDto(Patient patient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patient partialUpdate(PatientResponse patientResponse, @MappingTarget Patient patient);

    Patient toEntity(PatientRequest patientRequest);

    PatientRequest toDto1(Patient patient);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Patient partialUpdate(PatientRequest patientRequest, @MappingTarget Patient patient);
}