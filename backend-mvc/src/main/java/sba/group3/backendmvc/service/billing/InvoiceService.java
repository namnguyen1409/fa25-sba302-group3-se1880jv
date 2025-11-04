package sba.group3.backendmvc.service.billing;

import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.billing.InvoiceResponse;

public interface InvoiceService {
    InvoiceResponse filter(SearchFilter filter);

    InvoiceResponse getById(String invoiceId);

    InvoiceResponse create(InvoiceResponse invoiceRequest);

    InvoiceResponse update(String invoiceId, InvoiceResponse invoiceRequest);

    void delete(String invoiceId);
}
