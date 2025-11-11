package sba.group3.backendmvc.mapper.cms;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.cms.ContentRequest;
import sba.group3.backendmvc.dto.response.cms.ContentResponse;
import sba.group3.backendmvc.entity.cms.Content;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface ContentMapper {
    Content toEntity(ContentRequest contentRequest);

    ContentRequest toDto(Content content);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Content partialUpdate(ContentRequest contentRequest, @MappingTarget Content content);

    Content toEntity(ContentResponse contentResponse);

    ContentResponse toDto1(Content content);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Content partialUpdate(ContentResponse contentResponse, @MappingTarget Content content);
}