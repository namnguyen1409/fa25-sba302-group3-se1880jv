package sba.group3.backendmvc.controller.billing;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.billing.InvoiceResponse;
import sba.group3.backendmvc.service.billing.InvoiceService;

@Slf4j
@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Invoice Management", description = "APIs for managing invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<InvoiceResponse>> filter(
            @RequestBody SearchFilter filter
    ) {
        log.info("Filtering invoices with filter: {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<InvoiceResponse>builder()
                         .data(invoiceService.filter(filter))
                        .build()
        );
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<CustomApiResponse<InvoiceResponse>> getById(
            @PathVariable String invoiceId
    ) {
        log.info("Getting invoice by id {}", invoiceId);
        return ResponseEntity.ok(
                CustomApiResponse.<InvoiceResponse>builder()
                        .data(invoiceService.getById(invoiceId))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<InvoiceResponse>> create(
            @RequestBody InvoiceResponse invoiceRequest
    ) {
        log.info("Creating invoice with data: {}", invoiceRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<InvoiceResponse>builder()
                        .data(invoiceService.create(invoiceRequest))
                        .build()
        );
    }

    @PutMapping("/{invoiceId}")
    public ResponseEntity<CustomApiResponse<InvoiceResponse>> update(
            @PathVariable String invoiceId,
            @RequestBody InvoiceResponse invoiceRequest
    ) {
        log.info("Updating invoice with id {} and data: {}", invoiceId, invoiceRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<InvoiceResponse>builder()
                        .data(invoiceService.update(invoiceId, invoiceRequest))
                        .build()
        );
    }

    @DeleteMapping("/{invoiceId}")
    public ResponseEntity<CustomApiResponse<Void>> delete(
            @PathVariable String invoiceId
    ) {
        log.info("Deleting invoice with id {}", invoiceId);
        invoiceService.delete(invoiceId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Invoice deleted successfully")
                        .build()
        );
    }
}
