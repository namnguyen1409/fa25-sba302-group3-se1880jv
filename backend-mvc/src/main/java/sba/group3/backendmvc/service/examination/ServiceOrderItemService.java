package sba.group3.backendmvc.service.examination;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderItemRequest;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderItemResponse;

public interface ServiceOrderItemService {
    Page<ServiceOrderItemResponse> filter(SearchFilter filter);

    ServiceOrderItemResponse create(ServiceOrderItemRequest request);

    ServiceOrderItemResponse update(String id, ServiceOrderItemRequest request);

    void delete(String id);
}
