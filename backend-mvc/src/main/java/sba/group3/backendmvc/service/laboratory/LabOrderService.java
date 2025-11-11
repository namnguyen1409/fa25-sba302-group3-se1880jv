package sba.group3.backendmvc.service.laboratory;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabOrderRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabOrderResponse;

import java.util.List;
import java.util.UUID;

public interface LabOrderService {
    Page<LabOrderResponse> filter(SearchFilter filter);

    LabOrderResponse create(LabOrderRequest labOrderRequest);

    LabOrderResponse getById(UUID orderId);

    LabOrderResponse update(UUID orderId, LabOrderRequest labOrderRequest);

    void delete(UUID orderId);

    List<LabOrderResponse> createOrder(String examinationId, List<UUID> uuids);

    List<LabOrderResponse> filterList(SearchFilter filter);
}
