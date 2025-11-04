package sba.group3.backendmvc.service.laboratory.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabTestRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResponse;
import sba.group3.backendmvc.mapper.laboratory.LabTestMapper;
import sba.group3.backendmvc.repository.laboratory.LabTestRepository;
import sba.group3.backendmvc.service.laboratory.LabTestService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class LabTestServiceImpl implements LabTestService {
    private final LabTestRepository labTestRepository;
    private final LabTestMapper labTestMapper;

    @Override
    public Page<LabTestResponse> filter(SearchFilter filter) {
        return labTestRepository.search(filter).map(labTestMapper::toDto1);
    }

    @Override
    public LabTestResponse create(LabTestRequest labTestRequest) {
        var labTest = labTestMapper.toEntity(labTestRequest);
        labTest.setCode(generateTestCode());
        var savedTest = labTestRepository.save(labTest);
        return labTestMapper.toDto1(savedTest);
    }

    @Override
    public LabTestResponse update(UUID testId, LabTestRequest labTestRequest) {
        var labTest = labTestMapper.toEntity(labTestRequest);
        labTest.setId(testId);
        var updatedTest = labTestRepository.save(labTest);
        return labTestMapper.toDto1(updatedTest);
    }

    @Override
    public void delete(UUID testId) {
        var labTest = labTestRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Lab test not found"));
        labTest.setDeleted(true);
        labTestRepository.save(labTest);
    }

    @Override
    public LabTestResponse getById(UUID testId) {
        var labTest = labTestRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Lab test not found"));
        return labTestMapper.toDto1(labTest);
    }

    private String generateTestCode() {
        long count = labTestRepository.count();
        return String.format("LT%05d", count + 1);
    }
}
