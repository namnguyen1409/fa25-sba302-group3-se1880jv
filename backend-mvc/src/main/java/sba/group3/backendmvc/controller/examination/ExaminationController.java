package sba.group3.backendmvc.controller.examination;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.filter.Filter;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ExaminationRequest;
import sba.group3.backendmvc.dto.request.examination.PrescriptionRequest;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.ExaminationResponse;
import sba.group3.backendmvc.dto.response.examination.PrescriptionResponse;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderResponse;
import sba.group3.backendmvc.dto.response.laboratory.LabOrderResponse;
import sba.group3.backendmvc.entity.billing.Invoice;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.service.auth.JwtService;
import sba.group3.backendmvc.service.auth.impl.JwtServiceImpl;
import sba.group3.backendmvc.service.examination.ExaminationService;
import sba.group3.backendmvc.service.examination.PrescriptionService;
import sba.group3.backendmvc.service.examination.ServiceOrderItemService;
import sba.group3.backendmvc.service.examination.ServiceOrderService;
import sba.group3.backendmvc.service.laboratory.LabOrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/examinations")
public class ExaminationController {
    private final ExaminationService examinationService;
    private final ServiceOrderService serviceOrderService;
    private final ServiceOrderItemService serviceOrderItemService;
    private final PrescriptionService prescriptionService;
    private final LabOrderService labOrderService;
    private final JwtService jwtService;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<ExaminationResponse>>> getExaminations(
            @RequestBody SearchFilter filter,
            @AuthenticationPrincipal Jwt jwt
    ) {
        log.info("getExaminations called with filter: {}", filter);
        JwtServiceImpl.AuthInfo info = jwtService.extract(jwt);
        applyRoleBasedMandatoryFilter(filter, info);
        Page<ExaminationResponse> responseList = examinationService.filter(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<ExaminationResponse>>builder()
                        .data(responseList)
                        .build()
        );
    }

    private void applyRoleBasedMandatoryFilter(SearchFilter filter, JwtServiceImpl.AuthInfo info) {
        if (info.role().contains("ROLE_SYSTEM_ADMIN") || info.role().contains("ROLE_MANAGER")) {
            return;
        }
        if (info.role().contains("ROLE_PATIENT")) {
            filter.addMandatoryCondition(Filter.builder()
                    .field(Examination.Fields.patient + ".id")
                    .operator("eq")
                    .value(info.patientId())
                    .build());
            return;
        }

        if (info.role().contains("ROLE_DOCTOR")) {
            filter.addMandatoryCondition(Filter.builder()
                    .field(Examination.Fields.staff + ".id")
                    .operator("eq")
                    .value(info.staffId())
                    .build());
        }
    }


    @PostMapping
    public ResponseEntity<CustomApiResponse<ExaminationResponse>> createExamination(
            @RequestBody @Validated ExaminationRequest request
    ) {
        log.info("Creating new examination (check-in) with request: {}", request);
        ExaminationResponse response = examinationService.create(request);
        return ResponseEntity.ok(
                CustomApiResponse.<ExaminationResponse>builder()
                        .data(response)
                        .message("Examination created successfully")
                        .build()
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ExaminationResponse>> getExaminationDetail(
            @PathVariable String id
    ) {
        log.info("Getting detail for examination with id: {}", id);
        ExaminationResponse response = examinationService.getExaminationDetail(id);
        return ResponseEntity.ok(
                CustomApiResponse.<ExaminationResponse>builder()
                        .data(response)
                        .build()
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ExaminationResponse>> updateExamination(
            @PathVariable String id,
            @RequestBody @Validated ExaminationRequest request
    ) {
        log.info("Updating examination with id {}: {}", id, request);
        ExaminationResponse response = examinationService.update(id, request);
        return ResponseEntity.ok(
                CustomApiResponse.<ExaminationResponse>builder()
                        .data(response)
                        .message("Examination updated successfully")
                        .build()
        );
    }

    @PostMapping("/{examId}/services/filter")
    public ResponseEntity<CustomApiResponse<Page<ServiceOrderResponse>>> filterServiceOrders(
            @PathVariable UUID examId,
            @RequestBody @Validated SearchFilter filter
    ) {
        log.info("Filtering service orders for examination {}: {}", examId, filter);
        filter.addMandatoryCondition(
                Filter.builder()
                        .field("examination.id")
                        .operator("eq")
                        .value(examId)
                        .build()
        );
        Page<ServiceOrderResponse> responsePage = serviceOrderService.filter(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<ServiceOrderResponse>>builder()
                        .data(responsePage)
                        .build()
        );
    }

    @GetMapping("/{id}/services")
    public ResponseEntity<CustomApiResponse<ServiceOrderResponse>> getServiceOrders(
            @PathVariable("id") String examinationId
    ) {
        log.info("Getting service orders for examination with id: {}", examinationId);
        ServiceOrderResponse responseList = serviceOrderService.getServiceOrdersByExaminationId(examinationId);
        return ResponseEntity.ok(
                CustomApiResponse.<ServiceOrderResponse>builder()
                        .data(responseList)
                        .build()
        );
    }


    public record CreateServiceOrderRequest(
            List<UUID> serviceIds
    ) {}

    @PostMapping("/{examId}/services/orders")
    public ResponseEntity<CustomApiResponse<List<ServiceOrderResponse>>> createOrders(
            @PathVariable UUID examId,
            @RequestBody CreateServiceOrderRequest request
    ) {
        log.info("Creating service orders for examination {}: {}", examId, request);
        List<ServiceOrderResponse> responseList = serviceOrderService.createOrders(examId, request.serviceIds());
        return ResponseEntity.ok(
                CustomApiResponse.<List<ServiceOrderResponse>>builder()
                        .data(responseList)
                        .message("Service orders created successfully")
                        .build()
        );
    }

    @PostMapping("/{id}/services")
    public ResponseEntity<CustomApiResponse<ServiceOrderResponse>> createServiceOrder(
            @PathVariable("id") String examinationId,
            @RequestBody @Validated ServiceOrderRequest request
    ) {
        log.info("Creating new service order for examination {}: {}", examinationId, request);
        ServiceOrderResponse response = serviceOrderService.create(request);
        return ResponseEntity.ok(
                CustomApiResponse.<ServiceOrderResponse>builder()
                        .data(response)
                        .message("Service order created successfully")
                        .build()
        );
    }


    @PutMapping("/{id}/services/{serviceOrderId}")
    public ResponseEntity<CustomApiResponse<ServiceOrderResponse>> saveOrUpdateServiceOrders(
            @PathVariable("id") String examinationId,
            @PathVariable("serviceOrderId") String serviceOrderId,
            @RequestBody @Validated ServiceOrderRequest request
    ) {
        log.info("Saving/Updating service orders for examination {}: {}", examinationId, request);
        ServiceOrderResponse response = serviceOrderService.update(serviceOrderId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<ServiceOrderResponse>builder()
                        .data(response)
                        .message("Service orders saved/updated successfully")
                        .build()
        );
    }

    @DeleteMapping("/{id}/services/item/{itemId}")
    public ResponseEntity<CustomApiResponse<Void>> deleteServiceOrderItem(
            @PathVariable("id") String examinationId,
            @PathVariable("itemId") String itemId
    ) {
        log.info("Deleting service order item {} from examination {}", itemId, examinationId);
        serviceOrderItemService.delete(itemId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Service order item deleted successfully")
                        .build()
        );
    }

    @PostMapping("/{id}/prescription")
    public ResponseEntity<CustomApiResponse<PrescriptionResponse>> createPrescription(
            @PathVariable("id") String examinationId,
            @RequestBody @Validated PrescriptionRequest request
    ) {
        log.info("Creating prescription for examination with id {}: {}", examinationId, request);
        PrescriptionResponse response = prescriptionService.createForExamination(examinationId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<PrescriptionResponse>builder()
                        .data(response)
                        .message("Prescription created successfully")
                        .build()
        );
    }

    @GetMapping("/{id}/prescription")
    public ResponseEntity<CustomApiResponse<PrescriptionResponse>> getPrescription(
            @PathVariable("id") String examinationId
    ) {
        log.info("Getting prescription for examination with id: {}", examinationId);
        PrescriptionResponse response = prescriptionService.getPrescriptionByExaminationId(examinationId);
        return ResponseEntity.ok(
                CustomApiResponse.<PrescriptionResponse>builder()
                        .data(response)
                        .build()
        );
    }


    @PutMapping("/{id}/prescription/{prescriptionId}")
    public ResponseEntity<CustomApiResponse<PrescriptionResponse>> saveOrUpdatePrescription(
            @PathVariable("id") String examinationId,
            @PathVariable("prescriptionId") String prescriptionId,
            @RequestBody @Validated PrescriptionRequest request
    ) {
        log.info("Saving/Updating prescription for examination with id {}: {}", examinationId, request);
        PrescriptionResponse response = prescriptionService.update(prescriptionId, request);
        return ResponseEntity.ok(
                CustomApiResponse.<PrescriptionResponse>builder()
                        .data(response)
                        .message("Prescription saved/updated successfully")
                        .build()
        );
    }


    @DeleteMapping("/prescription/{prescriptionId}")
    public ResponseEntity<CustomApiResponse<Void>> deletePrescriptionItem(
            @PathVariable("prescriptionId") String prescriptionId
    ) {
        log.info("Deleting prescription item {}", prescriptionId);
        prescriptionService.delete(prescriptionId);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("Prescription item deleted successfully")
                        .build()
        );
    }

    public record CreateLabOrderRequest(
            List<UUID> labTestIds
    ) {}

    @PostMapping("/{id}/lab/orders")
    public ResponseEntity<CustomApiResponse<List<LabOrderResponse>>> createLabOrder(
            @PathVariable("id") String examinationId,
            @RequestBody @Validated CreateLabOrderRequest request
    ) {
        log.info("Creating new lab order for examination {}: {}", examinationId, request);
        List<LabOrderResponse> response = labOrderService.createOrder(examinationId, request.labTestIds());
        return ResponseEntity.ok(
                CustomApiResponse.<List<LabOrderResponse>>builder()
                        .data(response)
                        .message("Service order created successfully")
                        .build()
        );
    }

    @PostMapping("/{id}/lab/orders/filter")
    public ResponseEntity<CustomApiResponse<Page<LabOrderResponse>>> filterLabOrders(
            @PathVariable("id") UUID examinationId,
            @RequestBody @Validated SearchFilter filter
    ) {
        log.info("Filtering lab orders for examination {}: {}", examinationId, filter);
        filter.addMandatoryCondition(
                Filter.builder()
                        .field("examination.id")
                        .operator("eq")
                        .value(examinationId)
                        .build()
        );
        Page<LabOrderResponse> responsePage = labOrderService.filter(filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<LabOrderResponse>>builder()
                        .data(responsePage)
                        .build()
        );
    }


}
