package sba.group3.backendmvc.service.examination.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderItemRequest;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderItemResponse;
import sba.group3.backendmvc.entity.examination.ServiceOrderItem;
import sba.group3.backendmvc.mapper.examination.ServiceOrderItemMapper;
import sba.group3.backendmvc.repository.examination.ServiceCatalogRepository;
import sba.group3.backendmvc.repository.examination.ServiceOrderItemRepository;
import sba.group3.backendmvc.repository.examination.ServiceOrderRepository;
import sba.group3.backendmvc.service.examination.ServiceOrderItemService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceOrderItemServiceImpl implements ServiceOrderItemService {
    private final ServiceOrderItemRepository serviceOrderItemRepository;
    private final ServiceOrderItemMapper serviceOrderItemMapper;
    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceCatalogRepository serviceCatalogRepository;

    @Override
    public Page<ServiceOrderItemResponse> filter(SearchFilter filter) {
        return serviceOrderItemRepository.search(filter).map(serviceOrderItemMapper::toDto1);
    }

    @Override
    public ServiceOrderItemResponse create(ServiceOrderItemRequest request) {
        ServiceOrderItem serviceOrderItem = serviceOrderItemMapper.toEntity(request);
        return serviceOrderItemMapper.toDto1(serviceOrderItemRepository.save(serviceOrderItem));
    }

    @Override
    public ServiceOrderItemResponse update(String id, ServiceOrderItemRequest request) {
        ServiceOrderItem serviceOrderItem = serviceOrderItemRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("ServiceOrderItem with id " + id + " not found."));

        serviceOrderItemMapper.partialUpdate(request, serviceOrderItem);

        if( request.serviceOrderId() != null){
            serviceOrderItem.setServiceOrder(serviceOrderRepository.findById(request.serviceOrderId())
                    .orElseThrow(() -> new IllegalArgumentException("Service Order not found with id " + request.serviceOrderId())));
        }
        if( request.serviceId() != null){
            serviceOrderItem.setService(serviceCatalogRepository.findById(request.serviceId())
                    .orElseThrow(() -> new IllegalArgumentException("Service Catalog not found with id " + request.serviceId())));
        }

        ServiceOrderItem saved = serviceOrderItemRepository.save(serviceOrderItem);

        return serviceOrderItemMapper.toDto1(saved);
    }

    @Override
    public void delete(String id) {
        ServiceOrderItem serviceOrderItem = serviceOrderItemRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new IllegalArgumentException("ServiceOrderItem with id " + id + " not found."));
        serviceOrderItem.setDeleted(true);
        serviceOrderItemRepository.save(serviceOrderItem);
    }
}
