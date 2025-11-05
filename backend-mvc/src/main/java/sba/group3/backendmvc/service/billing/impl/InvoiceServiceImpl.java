package sba.group3.backendmvc.service.billing.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.billing.InvoiceRequest;
import sba.group3.backendmvc.dto.response.billing.InvoiceResponse;
import sba.group3.backendmvc.mapper.billing.InvoiceMapper;
import sba.group3.backendmvc.repository.billing.InvoiceRepository;
import sba.group3.backendmvc.service.billing.InvoiceService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceServiceImpl implements InvoiceService {
    private final InvoiceRepository invoiceRepository;
    private final InvoiceMapper invoiceMapper;

    @Override
    public Page<InvoiceResponse> filter(SearchFilter filter) {
        return invoiceRepository.search(filter).map(invoiceMapper::toDto1);
    }

    @Override
    public InvoiceResponse getById(UUID invoiceId) {
        var entity = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + invoiceId));
        return invoiceMapper.toDto1(entity);
    }

    @Override
    public InvoiceResponse create(InvoiceRequest invoiceRequest) {
        var entity = invoiceMapper.toEntity(invoiceRequest);
        var savedEntity = invoiceRepository.save(entity);
        return invoiceMapper.toDto1(savedEntity);
    }

    @Override
    public InvoiceResponse update(UUID invoiceId, InvoiceRequest invoiceRequest) {
        var existingEntity = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + invoiceId));
        invoiceMapper.partialUpdate(invoiceRequest, existingEntity);
        var updatedEntity = invoiceRepository.save(existingEntity);
        return invoiceMapper.toDto1(updatedEntity);
    }

    @Override
    public void delete(UUID invoiceId) {
        var existingEntity = invoiceRepository.findById(invoiceId)
                .orElseThrow(() -> new RuntimeException("Invoice not found with id: " + invoiceId));
        existingEntity.setDeleted(true);
        invoiceRepository.save(existingEntity);
    }

}
