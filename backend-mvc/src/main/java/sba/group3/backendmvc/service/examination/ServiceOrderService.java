package sba.group3.backendmvc.service.examination;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderRequest;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderResponse;

import java.util.List;
import java.util.UUID;

public interface ServiceOrderService {
    Page<ServiceOrderResponse> filter(SearchFilter filter);

    List<ServiceOrderResponse> filterList(SearchFilter filter);

    ServiceOrderResponse create(ServiceOrderRequest request);

    ServiceOrderResponse update(String id, ServiceOrderRequest request);

    void delete(String id);

    ServiceOrderResponse getServiceOrdersByExaminationId(String examinationId);

    List<ServiceOrderResponse> createOrders(UUID examId, List<UUID> uuids);

    ServiceOrderResponse getById(String id);
}
