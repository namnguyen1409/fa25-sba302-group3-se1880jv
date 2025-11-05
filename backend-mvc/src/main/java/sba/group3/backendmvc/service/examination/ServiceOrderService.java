package sba.group3.backendmvc.service.examination;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderRequest;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderResponse;

public interface ServiceOrderService {
    Page<ServiceOrderResponse> filter(SearchFilter filter);

    ServiceOrderResponse create(ServiceOrderRequest request);

    ServiceOrderResponse update(String id, ServiceOrderRequest request);

    void delete(String id);

    ServiceOrderResponse getServiceOrdersByExaminationId(String examinationId);
}
