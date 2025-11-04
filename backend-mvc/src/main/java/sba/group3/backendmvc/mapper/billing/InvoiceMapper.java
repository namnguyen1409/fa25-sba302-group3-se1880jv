package sba.group3.backendmvc.mapper.billing;

import org.mapstruct.*;
import sba.group3.backendmvc.dto.request.billing.InvoiceRequest;
import sba.group3.backendmvc.dto.response.billing.InvoiceResponse;
import sba.group3.backendmvc.entity.billing.Invoice;
import sba.group3.backendmvc.mapper.examination.ExaminationMapper;
import sba.group3.backendmvc.mapper.patient.PatientMapper;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING, uses = {PatientMapper.class, ExaminationMapper.class, InvoiceItemMapper.class})
public interface InvoiceMapper {
    @Mapping(source = "examinationId", target = "examination.id")
    @Mapping(source = "patientId", target = "patient.id")
    Invoice toEntity(InvoiceRequest invoiceRequest);

    @InheritInverseConfiguration(name = "toEntity")
    InvoiceRequest toDto(Invoice invoice);

    @InheritConfiguration(name = "toEntity")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Invoice partialUpdate(InvoiceRequest invoiceRequest, @MappingTarget Invoice invoice);

    Invoice toEntity(InvoiceResponse invoiceResponse);

    @AfterMapping
    default void linkItems(@MappingTarget Invoice invoice) {
        invoice.getItems().forEach(item -> item.setInvoice(invoice));
    }

    InvoiceResponse toDto1(Invoice invoice);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Invoice partialUpdate(InvoiceResponse invoiceResponse, @MappingTarget Invoice invoice);
}