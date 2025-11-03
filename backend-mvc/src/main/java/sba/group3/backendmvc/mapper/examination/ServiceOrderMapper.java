package sba.group3.backendmvc.mapper.examination;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderRequest;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderResponse;
import sba.group3.backendmvc.entity.examination.ServiceOrder;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {ServiceOrderItemMapper.class})
public interface ServiceOrderMapper {
    @Mapping(source = "examinationId", target = "examination.id")
    ServiceOrder toEntity(ServiceOrderRequest serviceOrderRequest);

    @Mapping(source = "examination.id", target = "examinationId")
    ServiceOrderRequest toDto(ServiceOrder serviceOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "examinationId", target = "examination.id")
    ServiceOrder partialUpdate(ServiceOrderRequest serviceOrderRequest, @MappingTarget ServiceOrder serviceOrder);

    ServiceOrder toEntity(ServiceOrderResponse serviceOrderResponse);

    @AfterMapping
    default void linkItems(@MappingTarget ServiceOrder serviceOrder) {
        serviceOrder.getItems().forEach(item -> item.setServiceOrder(serviceOrder));
    }

    ServiceOrderResponse toDto1(ServiceOrder serviceOrder);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    ServiceOrder partialUpdate(ServiceOrderResponse serviceOrderResponse, @MappingTarget ServiceOrder serviceOrder);
}