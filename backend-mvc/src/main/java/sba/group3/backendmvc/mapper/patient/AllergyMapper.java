package sba.group3.backendmvc.mapper.patient;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.patient.AllergyRequest;
import sba.group3.backendmvc.dto.response.patient.AllergyResponse;
import sba.group3.backendmvc.entity.patient.Allergy;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface AllergyMapper {
    Allergy toEntity(AllergyResponse allergyResponse);

    AllergyResponse toDto(Allergy allergy);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Allergy partialUpdate(AllergyResponse allergyResponse, @MappingTarget Allergy allergy);

    Allergy toEntity(AllergyRequest allergyRequest);

    AllergyRequest toDto1(Allergy allergy);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Allergy partialUpdate(AllergyRequest allergyRequest, @MappingTarget Allergy allergy);
}