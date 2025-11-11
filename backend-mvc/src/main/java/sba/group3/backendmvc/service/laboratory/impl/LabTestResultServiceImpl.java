package sba.group3.backendmvc.service.laboratory.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabTestResultRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResultResponse;
import sba.group3.backendmvc.entity.laboratory.LabStatus;
import sba.group3.backendmvc.mapper.laboratory.LabTestResultMapper;
import sba.group3.backendmvc.repository.laboratory.LabOrderRepository;
import sba.group3.backendmvc.repository.laboratory.LabTestRepository;
import sba.group3.backendmvc.repository.laboratory.LabTestResultRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.service.examination.ExaminationService;
import sba.group3.backendmvc.service.laboratory.LabTestResultService;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class LabTestResultServiceImpl implements LabTestResultService {
    private final LabTestResultRepository labTestResultRepository;
    private final LabTestResultMapper labTestResultMapper;
    private final LabTestRepository labTestRepository;
    private final LabOrderRepository labOrderRepository;
    private final StaffRepository staffRepository;
    private final ExaminationService examinationService;

    @Override
    public Page<LabTestResultResponse> filter(SearchFilter filter) {
        return labTestResultRepository.search(filter).map(labTestResultMapper::toDto1);
    }

    @Override
    public LabTestResultResponse create(UUID orderId, LabTestResultRequest labTestResultRequest) {
        var entity = labTestResultMapper.toEntity(labTestResultRequest);
        entity.setLabTest(labTestRepository.getReferenceById(labTestResultRequest.labTestId()));
        entity.setLabOrder(labOrderRepository.getReferenceById(labTestResultRequest.labOrderId()));
        var savedEntity = labTestResultRepository.save(entity);
        return labTestResultMapper.toDto1(savedEntity);
    }

    @Override
    public void deleteFromOrder(UUID orderId, UUID itemId) {

        var existingEntity = labTestResultRepository.findByIdAndLabOrderId(itemId, orderId)
                .orElseThrow(() -> new RuntimeException("Lab Test Result not found for the given order"));
        existingEntity.setDeleted(true);
        labTestResultRepository.save(existingEntity);
    }

    @Transactional
    @Override
    public LabTestResultResponse update(UUID itemId, LabTestResultRequest labTestResultRequest) {
        var existingEntity = labTestResultRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Lab Test Result not found"));
        labTestResultMapper.partialUpdate(labTestResultRequest, existingEntity);
        var updatedEntity = labTestResultRepository.save(existingEntity);
        return labTestResultMapper.toDto1(updatedEntity);
    }

    @Transactional
    @Override
    public void verify(UUID itemId, UUID staffId) {
        var existingEntity = labTestResultRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Lab Test Result not found"));
        existingEntity.setVerifiedBy(staffRepository.getReferenceById(staffId));
        existingEntity.setVerifiedAt(LocalDateTime.now());
        existingEntity.setStatus(LabStatus.VERIFIED);
        var labOrder = existingEntity.getLabOrder();
        boolean done = labOrder.getResults().stream().allMatch(o -> o.getStatus().equals(LabStatus.VERIFIED));
        if (done) {
            labOrder.setStatus(LabStatus.COMPLETED);
            examinationService.handleOrderStatusChanged(labOrder.getExamination().getId());
        }
        labTestResultRepository.save(existingEntity);
    }
}
