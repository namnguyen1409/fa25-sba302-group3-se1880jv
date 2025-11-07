package sba.group3.backendmvc.mapper.common;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.common.FileAttachmentResponse;
import sba.group3.backendmvc.entity.common.FileAttachment;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface FileAttachmentMapper {
    FileAttachment toEntity(FileAttachmentResponse fileAttachmentResponse);

    FileAttachmentResponse toDto(FileAttachment fileAttachment);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    FileAttachment partialUpdate(FileAttachmentResponse fileAttachmentResponse, @MappingTarget FileAttachment fileAttachment);
}