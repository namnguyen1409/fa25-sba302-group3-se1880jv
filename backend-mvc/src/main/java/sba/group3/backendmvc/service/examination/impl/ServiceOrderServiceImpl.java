package sba.group3.backendmvc.service.examination.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ServiceOrderRequest;
import sba.group3.backendmvc.dto.response.examination.ServiceOrderResponse;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.ServiceOrder;
import sba.group3.backendmvc.mapper.examination.ServiceOrderMapper;
import sba.group3.backendmvc.repository.examination.ServiceOrderRepository;
import sba.group3.backendmvc.repository.patient.ExaminationRepository;
import sba.group3.backendmvc.service.examination.ServiceOrderService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ServiceOrderServiceImpl implements ServiceOrderService {
    private final ServiceOrderRepository serviceOrderRepository;
    private final ServiceOrderMapper serviceOrderMapper;
    private final ExaminationRepository examinationRepository;

    @Override
    public Page<ServiceOrderResponse> filter(SearchFilter filter) {
        return serviceOrderRepository.search(filter).map(serviceOrderMapper::toDto1);
    }

    @Override
    public ServiceOrderResponse create(ServiceOrderRequest request) {
        ServiceOrder serviceOrder = serviceOrderMapper.toEntity(request);
        return serviceOrderMapper.toDto1(serviceOrderRepository.save(serviceOrder));
    }

    @Override
    public ServiceOrderResponse update(String id, ServiceOrderRequest request) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("ServiceOrder with id " + id + " not found."));

        serviceOrderMapper.partialUpdate(request, serviceOrder);

        if( request.examinationId() != null){
            serviceOrder.setExamination(examinationRepository.findById(request.examinationId())
                    .orElseThrow(() -> new IllegalArgumentException("Examination not found with id " + request.examinationId())));
        }
        ServiceOrder saved = serviceOrderRepository.save(serviceOrder);


        return serviceOrderMapper.toDto1(saved);
    }

    @Override
    public void delete(String id) {
        ServiceOrder serviceOrder = serviceOrderRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new IllegalArgumentException("ServiceOrder with id " + id + " not found."));
        serviceOrder.setDeleted(true);
        serviceOrderRepository.save(serviceOrder);
    }

    @Override
    public ServiceOrderResponse getServiceOrdersByExaminationId(String examinationId) {
        Examination examination = examinationRepository.findById(UUID.fromString(examinationId)).orElseThrow(()->
                new IllegalArgumentException("Examination with id " + examinationId + " not found."));
        ServiceOrder serviceOrder = serviceOrderRepository.findByExamination(((examination));
        if(serviceOrder == null) {
            throw new IllegalArgumentException("ServiceOrder for Examination id " + examinationId + " not found.");
        }

        return serviceOrderMapper.toDto1(serviceOrder);
    }
}
