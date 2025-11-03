package sba.group3.backendmvc.mapper.examination;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.examination.ServiceCatalogResponse;
import sba.group3.backendmvc.entity.examination.ServiceCatalog;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)public interface ServiceCatalogMapper {
    ServiceCatalog toEntity(ServiceCatalogResponse serviceCatalogResponse);

    ServiceCatalogResponse toDto(ServiceCatalog serviceCatalog);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)ServiceCatalog partialUpdate(ServiceCatalogResponse serviceCatalogResponse, @MappingTarget ServiceCatalog serviceCatalog);
}