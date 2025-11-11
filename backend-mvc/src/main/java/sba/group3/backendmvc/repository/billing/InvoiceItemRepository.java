package sba.group3.backendmvc.repository.billing;

import sba.group3.backendmvc.entity.billing.InvoiceItem;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface InvoiceItemRepository extends BaseRepository<InvoiceItem, UUID> {
}