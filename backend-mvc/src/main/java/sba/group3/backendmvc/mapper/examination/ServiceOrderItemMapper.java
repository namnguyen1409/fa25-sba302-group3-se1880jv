package sba.group3.backendmvc.mapper.examination;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderItemResponse;
import sba.group3.backendmvc.entity.examination.ServiceOrderItem;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderItemRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ServiceCatalogMapper.class})
public interface ServiceOrderItemMapper {
    @Mapping(source = "serviceId", target = "service.id")
    @Mapping(source = "serviceOrderId", target = "serviceOrder.id")
    ServiceOrderItem toEntity(ServiceOrderItemRequest serviceOrderItemRequest);

    @InheritInverseConfiguration(name = "toEntity")
    ServiceOrderItemRequest toDto(ServiceOrderItem serviceOrderItem);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ServiceOrderItem partialUpdate(ServiceOrderItemRequest serviceOrderItemRequest, @MappingTarget ServiceOrderItem serviceOrderItem);

    ServiceOrderItem toEntity(ServiceOrderItemResponse serviceOrderItemResponse);

    ServiceOrderItemResponse toDto1(ServiceOrderItem serviceOrderItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ServiceOrderItem partialUpdate(ServiceOrderItemResponse serviceOrderItemResponse, @MappingTarget ServiceOrderItem serviceOrderItem);
}