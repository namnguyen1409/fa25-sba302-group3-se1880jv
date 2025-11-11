package sba.group3.backendmvc.service.laboratory.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.laboratory.LabTestRequest;
import sba.group3.backendmvc.dto.response.laboratory.LabTestResponse;
import sba.group3.backendmvc.entity.laboratory.LabTest;
import sba.group3.backendmvc.mapper.laboratory.LabTestMapper;
import sba.group3.backendmvc.repository.laboratory.LabTestRepository;
import sba.group3.backendmvc.service.laboratory.LabTestService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class LabTestServiceImpl implements LabTestService {
    private final LabTestRepository labTestRepository;
    private final LabTestMapper labTestMapper;

    @Transactional
    @PostConstruct
    public void init() {
        if (labTestRepository.count() == 0) {
            try{
                AtomicLong initialCount = new AtomicLong();
                var resource = new ClassPathResource("lab_tests.json");
                String prefix = "LT";
                ObjectMapper mapper = new ObjectMapper();
                List<LabTest> labTests = mapper.readValue(
                        resource.getInputStream(), mapper.getTypeFactory().constructCollectionType(List.class, LabTest.class)
                );
                Random random = new Random();
                for (LabTest labTest : labTests) {
                    labTest.setCode(String.format("%s%05d", prefix, initialCount.incrementAndGet()));
                    if (labTest.getPrice() == null) {
                        int min = 40000;
                        int max = 400000;
                        int value = ((random.nextInt(max - min) + min) / 1000) * 1000;
                        labTest.setPrice(BigDecimal.valueOf(value));
                    }
                    if (labTest.getUnit() == null || labTest.getUnit().isBlank()) {
                        labTest.setUnit("N/A");
                    }
                    if (labTest.getReferenceRange() == null || labTest.getReferenceRange().isBlank()) {
                        labTest.setReferenceRange("N/A");
                    }
                }
                labTestRepository.saveAll(labTests);
                log.info("Imported {} lab tests", labTests.size());
            } catch (Exception e) {
                log.error("Failed to import lab tests", e);
            }
        }
    }


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

    @Transactional
    @Override
    public LabTestResponse update(UUID testId, LabTestRequest labTestRequest) {
        var labTest = labTestRepository.findById(testId)
                .orElseThrow(() -> new RuntimeException("Lab test not found"));
        labTestMapper.partialUpdate(labTestRequest, labTest);
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
