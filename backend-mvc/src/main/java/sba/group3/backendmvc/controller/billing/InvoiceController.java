package sba.group3.backendmvc.controller.billing;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.*;
import sba.group3.backendmvc.dto.request.billing.InvoiceRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.billing.InvoiceResponse;
import sba.group3.backendmvc.dto.response.laboratory.LabOrderResponse;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.billing.Invoice;
import sba.group3.backendmvc.entity.examination.ServiceOrder;
import sba.group3.backendmvc.entity.examination.ServiceOrderStatus;
import sba.group3.backendmvc.service.billing.InvoiceService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/invoices")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "Invoice Management", description = "APIs for managing invoices")
public class InvoiceController {

    private final InvoiceService invoiceService;

    @GetMapping("/staff/today")
    public ResponseEntity<CustomApiResponse<List<InvoiceResponse>>> getOrdersForStaffToday(
            @AuthenticationPrincipal Jwt jwt
    ) {
        UUID staffId = UUID.fromString(jwt.getClaimAsString("staffId"));
        log.info("Fetching today's service orders for staff with id: {}", staffId);
        Instant now = Instant.now();
        ZoneId zone = ZoneId.of("Asia/Ho_Chi_Minh");
        LocalDate today = now.atZone(zone).toLocalDate();
        SearchFilter filter = SearchFilter.builder()
                .filterGroup(FilterGroup.builder()
                        .operator(LogicalOperator.AND)
                        .filters(
                                List.of(
                                        Filter.builder()
                                                .field(Invoice.Fields.assignedStaff + ".id")
                                                .operator("eq")
                                                .value(staffId)
                                                .build(),
                                        Filter.builder()
                                                .field(BaseEntity.Fields.createdDate)
                                                .operator("between")
                                                .value(List.of(
                                                        today.atStartOfDay(zone).toInstant(),
                                                        today.plusDays(1).atStartOfDay(zone).toInstant()
                                                ))
                                                .build()
                                        ,
                                        Filter.builder()
                                                .field(Invoice.Fields.paid)
                                                .operator("eq")
                                                .value(
                                                        false
                                                )
                                                .build()
                                )
                        )
                        .build())
                .sorts(List.of(
                        SortRequest.builder()
                                .field(BaseEntity.Fields.createdDate)
                                .direction(Sort.Direction.ASC)
                                .build()
                ))
                .build();
        List<InvoiceResponse> responseList = invoiceService.filterList(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<List<InvoiceResponse>>builder()
                        .data(responseList)
                        .build()
        );
    }

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<InvoiceResponse>>> filter(
            @RequestBody SearchFilter filter
    ) {
        log.info("Filtering invoices with filter: {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<InvoiceResponse>>builder()
                         .data(invoiceService.filter(filter))
                        .build()
        );
    }

    @GetMapping("/{invoiceId}")
    public ResponseEntity<CustomApiResponse<InvoiceResponse>> getById(
            @PathVariable UUID invoiceId
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
            @RequestBody InvoiceRequest invoiceRequest
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
            @PathVariable UUID invoiceId,
            @RequestBody InvoiceRequest invoiceRequest
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
            @PathVariable UUID invoiceId
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
