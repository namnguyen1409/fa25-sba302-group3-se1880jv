package sba.group3.backendmvc.controller.examination;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestClient;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ServiceCatalogRequest;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.examination.ServiceCatalogResponse;
import sba.group3.backendmvc.service.examination.ServiceCatalogService;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/examinations/service-catalog")
public class ServiceCatalogController {
    private final ServiceCatalogService serviceCatalogService;
    private final RestClient.Builder builder;

    @PostMapping("/filter")
    public ResponseEntity<CustomApiResponse<Page<ServiceCatalogResponse>>> getListServiceCatalogs(@RequestBody SearchFilter filter) {
        log.info("getListServiceCatalogs called with filter: {}", filter);
        return ResponseEntity.ok(
                CustomApiResponse.<Page<ServiceCatalogResponse>>builder()
                        .data(serviceCatalogService.filter(filter))
                        .build()
        );
    }

    @PostMapping
    public ResponseEntity<CustomApiResponse<ServiceCatalogResponse>> create(
            @RequestBody @Validated ServiceCatalogRequest serviceCatalogRequest
    ){
        log.info("Creating ServiceCatalog {}", serviceCatalogRequest);
        ServiceCatalogResponse response = serviceCatalogService.create(serviceCatalogRequest);
        return ResponseEntity.ok(
                CustomApiResponse.<ServiceCatalogResponse>builder()
                        .data(response)
                        .build()
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<ServiceCatalogResponse>> update(
            @PathVariable String id,
            @RequestBody @Validated ServiceCatalogRequest request
    ){
        log.info("Updating serviceCatalog with id {}: {}", id, request);
        ServiceCatalogResponse response = serviceCatalogService.update(id, request);
        return ResponseEntity.ok(
                CustomApiResponse.<ServiceCatalogResponse>builder()
                        .data(response)
                        .build()
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomApiResponse<Void>> delete(
            @PathVariable String id
    ){
        log.info("Deleting serviceCatalog with id {}", id);
        serviceCatalogService.delete(id);
        return ResponseEntity.ok(
                CustomApiResponse.<Void>builder()
                        .message("ServiceCatalog deleted successfully")
                        .build()
        );
    }
}

