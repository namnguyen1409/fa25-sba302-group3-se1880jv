package sba.group3.backendmvc.repository.billing;

import sba.group3.backendmvc.entity.billing.Invoice;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface InvoiceRepository extends BaseRepository<Invoice, UUID> {
}