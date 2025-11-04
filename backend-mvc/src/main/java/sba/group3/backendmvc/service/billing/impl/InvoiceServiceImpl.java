package sba.group3.backendmvc.service.billing.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.billing.InvoiceResponse;
import sba.group3.backendmvc.service.billing.InvoiceService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceServiceImpl implements InvoiceService {
    @Override
    public InvoiceResponse filter(SearchFilter filter) {
        return null;
    }

    @Override
    public InvoiceResponse getById(String invoiceId) {
        return null;
    }

    @Override
    public InvoiceResponse create(InvoiceResponse invoiceRequest) {
        return null;
    }

    @Override
    public InvoiceResponse update(String invoiceId, InvoiceResponse invoiceRequest) {
        return null;
    }

    @Override
    public void delete(String invoiceId) {

    }
}
