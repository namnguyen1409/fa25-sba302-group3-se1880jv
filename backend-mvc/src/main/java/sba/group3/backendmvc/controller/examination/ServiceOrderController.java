package sba.group3.backendmvc.controller.examination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.*;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderResponse;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.examination.ServiceOrder;
import sba.group3.backendmvc.entity.examination.ServiceOrderStatus;
import sba.group3.backendmvc.service.examination.ServiceOrderService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/service-orders")
public class ServiceOrderController {
    private final ServiceOrderService serviceOrderService;

    @GetMapping("/staff/today")
    public ResponseEntity<CustomApiResponse<List<ServiceOrderResponse>>> getServiceOrdersForStaffToday(
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
                                                .field(ServiceOrder.Fields.assignedStaff + ".id")
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
                                                .field(ServiceOrder.Fields.status)
                                                .operator("notIn")
                                                .value(
                                                        List.of(
                                                                ServiceOrderStatus.CANCELLED,
                                                                ServiceOrderStatus.COMPLETED
                                                        )
                                                )
                                                .build()
                                )
                        )
                        .build())
                .sorts(List.of(
                        SortRequest.builder()
                                .field(BaseEntity.Fields.createdDate)
                                .direction(Sort.Direction.DESC)
                                .build()
                ))
                .build();
        List<ServiceOrderResponse> responseList = serviceOrderService.filterList(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<List<ServiceOrderResponse>>builder()
                        .data(responseList)
                        .build()
        );
    }

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<ServiceOrderResponse>>> getServiceOrdersForFilters(
            @AuthenticationPrincipal Jwt jwt,
            @RequestBody SearchFilter filter
    ) {
        filter.addMandatoryCondition(
                Filter.builder()
                        .field(ServiceOrder.Fields.assignedStaff + ".id")
                        .operator("eq")
                        .value(UUID.fromString(jwt.getClaimAsString("staffId")))
                        .build()
        );
        return ResponseEntity.ok(
                CustomApiResponse.<Page<ServiceOrderResponse>>builder()
                        .data(serviceOrderService.filter(filter))
                        .message("")
                        .build()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ServiceOrderResponse>> getServiceOrderDetail(
            @PathVariable String id
    ) {
        log.info("Getting detail for service order with id: {}", id);
        ServiceOrderResponse response = serviceOrderService.getById(id);
        return ResponseEntity.ok(
                CustomApiResponse.<ServiceOrderResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ServiceOrderResponse>> updateServiceOrder(
            @PathVariable String id,
            @RequestBody ServiceOrderRequest request
    ) {
        log.info("Updating service order with id: {}", id);
        ServiceOrderResponse response = serviceOrderService.update(id, request);
        return ResponseEntity.ok(
                CustomApiResponse.<ServiceOrderResponse>builder()
                        .data(response)
                        .build()
        );
    }


}
