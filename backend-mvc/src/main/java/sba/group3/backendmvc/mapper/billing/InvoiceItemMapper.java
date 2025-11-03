package sba.group3.backendmvc.mapper.billing;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.billing.InvoiceItemRequest;
import sba.group3.backendmvc.dto.response.billing.InvoiceItemResponse;
import sba.group3.backendmvc.entity.billing.InvoiceItem;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InvoiceItemMapper {
    @Mapping(source = "invoiceId", target = "invoice.id")
    InvoiceItem toEntity(InvoiceItemRequest invoiceItemRequest);

    @Mapping(source = "invoice.id", target = "invoiceId")
    InvoiceItemRequest toDto(InvoiceItem invoiceItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(source = "invoiceId", target = "invoice.id")
    InvoiceItem partialUpdate(InvoiceItemRequest invoiceItemRequest, @MappingTarget InvoiceItem invoiceItem);

    InvoiceItem toEntity(InvoiceItemResponse invoiceItemResponse);

    InvoiceItemResponse toDto1(InvoiceItem invoiceItem);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    InvoiceItem partialUpdate(InvoiceItemResponse invoiceItemResponse, @MappingTarget InvoiceItem invoiceItem);
}