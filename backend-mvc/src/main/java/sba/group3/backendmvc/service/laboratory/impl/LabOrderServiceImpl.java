package sba.group3.backendmvc.service.laboratory.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabOrderRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabOrderResponse;
import sba.group3.backendmvc.mapper.laboratory.LabOrderMapper;
import sba.group3.backendmvc.repository.laboratory.LabOrderRepository;
import sba.group3.backendmvc.repository.patient.ExaminationRepository;
import sba.group3.backendmvc.repository.patient.PatientRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.service.laboratory.LabOrderService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class LabOrderServiceImpl implements LabOrderService {
    private final LabOrderRepository labOrderRepository;
    private final LabOrderMapper labOrderMapper;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    private final ExaminationRepository examinationRepository;

    @Override
    public Page<LabOrderResponse> filter(SearchFilter filter) {
        return labOrderRepository.search(filter).map(labOrderMapper::toDto1);
    }

    @Override
    public LabOrderResponse create(LabOrderRequest labOrderRequest) {
        var entity = labOrderMapper.toEntity(labOrderRequest);
        entity.setPatient(patientRepository.getReferenceById(labOrderRequest.patientId()));
        entity.setRequestedBy(staffRepository.getReferenceById(labOrderRequest.requestedById()));
        entity.setExamination(examinationRepository.getReferenceById(labOrderRequest.examinationId()));
        entity = labOrderRepository.save(entity);
        return labOrderMapper.toDto1(entity);
    }

    @Override
    public LabOrderResponse getById(UUID orderId) {
        var entity = labOrderRepository.findById(orderId).orElseThrow();
        return labOrderMapper.toDto1(entity);
    }

    @Override
    public LabOrderResponse update(UUID orderId, LabOrderRequest labOrderRequest) {
        var entity = labOrderRepository.findById(orderId).orElseThrow();
        labOrderMapper.partialUpdate(labOrderRequest, entity);
        entity = labOrderRepository.save(entity);
        return labOrderMapper.toDto1(entity);
    }

    @Override
    public void delete(UUID orderId) {
        var entity = labOrderRepository.findById(orderId).orElseThrow();
        entity.setDeleted(true);
        labOrderRepository.save(entity);
    }
}
