package sba.group3.backendmvc.mapper.common;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.common.IcdCodeRequest;
import sba.group3.backendmvc.dto.response.common.IcdCodeResponse;
import sba.group3.backendmvc.entity.common.IcdCode;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface IcdCodeMapper {
    IcdCode toEntity(IcdCodeRequest icdCodeRequest);

    IcdCodeRequest toDto(IcdCode icdCode);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    IcdCode partialUpdate(IcdCodeRequest icdCodeRequest, @MappingTarget IcdCode icdCode);

    IcdCode toEntity(IcdCodeResponse icdCodeResponse);

    IcdCodeResponse toDto1(IcdCode icdCode);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    IcdCode partialUpdate(IcdCodeResponse icdCodeResponse, @MappingTarget IcdCode icdCode);
}