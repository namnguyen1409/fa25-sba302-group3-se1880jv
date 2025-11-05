package sba.group3.backendmvc.service.billing;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.billing.InvoiceRequest;
import sba.group3.backendmvc.dto.response.billing.InvoiceResponse;

import java.util.UUID;

public interface InvoiceService {
    Page<InvoiceResponse> filter(SearchFilter filter);

    InvoiceResponse getById(UUID invoiceId);

    InvoiceResponse create(InvoiceRequest invoiceRequest);

    InvoiceResponse update(UUID invoiceId, InvoiceRequest invoiceRequest);

    void delete(UUID invoiceId);
}
