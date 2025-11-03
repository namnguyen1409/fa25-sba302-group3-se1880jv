package sba.group3.backendmvc.service.examination;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ServiceCatalogRequest;
import sba.group3.backendmvc.dto.response.examination.ServiceCatalogResponse;

import java.util.List;

public interface ServiceCatalogService {
    Page<ServiceCatalogResponse> filter(SearchFilter filter);

    List<ServiceCatalogResponse> getListServiceCatalogs(SearchFilter filter);

    ServiceCatalogResponse create(ServiceCatalogRequest serviceCatalogRequest);

    ServiceCatalogResponse update(String id, ServiceCatalogRequest request);

    void delete(String id);
}
