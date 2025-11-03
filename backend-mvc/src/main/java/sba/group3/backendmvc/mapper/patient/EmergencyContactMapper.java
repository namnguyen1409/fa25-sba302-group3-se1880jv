package sba.group3.backendmvc.mapper.patient;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.patient.EmergencyContactRequest;
import sba.group3.backendmvc.dto.response.patient.EmergencyContactResponse;
import sba.group3.backendmvc.entity.patient.EmergencyContact;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface EmergencyContactMapper {
    EmergencyContact toEntity(EmergencyContactResponse emergencyContactResponse);

    EmergencyContactResponse toDto(EmergencyContact emergencyContact);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmergencyContact partialUpdate(EmergencyContactResponse emergencyContactResponse, @MappingTarget EmergencyContact emergencyContact);

    EmergencyContact toEntity(EmergencyContactRequest emergencyContactRequest);

    EmergencyContactRequest toDto1(EmergencyContact emergencyContact);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    EmergencyContact partialUpdate(EmergencyContactRequest emergencyContactRequest, @MappingTarget EmergencyContact emergencyContact);
}