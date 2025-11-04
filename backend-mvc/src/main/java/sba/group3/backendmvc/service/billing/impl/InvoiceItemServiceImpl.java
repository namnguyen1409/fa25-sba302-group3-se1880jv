package sba.group3.backendmvc.service.billing.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.service.billing.InvoiceItemService;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class InvoiceItemServiceImpl implements InvoiceItemService {
}
