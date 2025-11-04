package sba.group3.backendmvc.service.laboratory.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabTestResultRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResultResponse;
import sba.group3.backendmvc.mapper.laboratory.LabTestResultMapper;
import sba.group3.backendmvc.repository.laboratory.LabOrderRepository;
import sba.group3.backendmvc.repository.laboratory.LabTestRepository;
import sba.group3.backendmvc.repository.laboratory.LabTestResultRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.service.laboratory.LabTestResultService;

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

    @Override
    public LabTestResultResponse update(UUID itemId, LabTestResultRequest labTestResultRequest) {
        var existingEntity = labTestResultRepository.findById(itemId)
                .orElseThrow(() -> new RuntimeException("Lab Test Result not found"));
        labTestResultMapper.partialUpdate(labTestResultRequest, existingEntity);
        if (labTestResultRequest.verifiedById() != null) {
            existingEntity.setVerifiedBy(
                    staffRepository.getReferenceById(labTestResultRequest.verifiedById())
            );
        }
        var updatedEntity = labTestResultRepository.save(existingEntity);
        return labTestResultMapper.toDto1(updatedEntity);
    }
}
