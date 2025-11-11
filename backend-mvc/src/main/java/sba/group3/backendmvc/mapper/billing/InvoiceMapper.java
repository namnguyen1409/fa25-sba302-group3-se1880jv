package sba.group3.backendmvc.mapper.billing;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.billing.InvoiceRequest;
import sba.group3.backendmvc.entity.billing.Invoice;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface InvoiceMapper {
    @Mapping(source = "examinationId", target = "examination.id")
    @Mapping(source = "patientId", target = "patient.id")
    Invoice toEntity(InvoiceRequest invoiceRequest);

    @InheritInverseConfiguration(name = "toEntity")
    InvoiceRequest toDto(Invoice invoice);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Invoice partialUpdate(InvoiceRequest invoiceRequest, @MappingTarget Invoice invoice);
}