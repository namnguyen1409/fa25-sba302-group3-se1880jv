package sba.group3.backendmvc.service.pharmacy.impl;

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
import sba.group3.backendmvc.dto.request.pharmacy.MedicineRequest;
import sba.group3.backendmvc.dto.response.pharmacy.MedicineResponse;
import sba.group3.backendmvc.entity.pharmacy.Medicine;
import sba.group3.backendmvc.mapper.pharmacy.MedicineMapper;
import sba.group3.backendmvc.repository.pharmacy.MedicineRepository;
import sba.group3.backendmvc.service.pharmacy.MedicineService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;


@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;

    @Transactional
    @PostConstruct
    public void init() {
        if (medicineRepository.count() == 0) {
            try{
                AtomicLong initialCount = new AtomicLong();
                var resource = new ClassPathResource("medicines.json");
                String prefix = "MED";
                ObjectMapper mapper = new ObjectMapper();
                List<Medicine> medicines = mapper.readValue(
                        resource.getInputStream(), mapper.getTypeFactory().constructCollectionType(List.class, Medicine.class)
                );
                Random random = new Random();
                for (Medicine medicine : medicines) {
                    medicine.setCode(String.format("%s%05d", prefix, initialCount.incrementAndGet()));
                    if (medicine.getPrice() == null) {
                        int[] steps = {1000, 2000, 5000};
                        int step = steps[random.nextInt(steps.length)];
                        int value = (random.nextInt(500) + 5) * step;
                        medicine.setPrice(BigDecimal.valueOf(value));
                    }
                }
                medicineRepository.saveAll(medicines);
                log.info("Imported {} medicines", medicines.size());
            } catch (Exception e) {
                log.error("Failed to import medicines", e);
            }
        }
    }

    @Override
    public Page<MedicineResponse> filter(SearchFilter filter) {
        return medicineRepository.search(filter).map(medicineMapper::toDto1);
    }

    @Override
    public MedicineResponse create(MedicineRequest medicineRequest) {
        var medicine = medicineMapper.toEntity(medicineRequest);
        medicine.setCode(generateMedicineCode());
        var savedMedicine = medicineRepository.save(medicine);
        return medicineMapper.toDto1(savedMedicine);
    }

    @Override
    public MedicineResponse getById(UUID medicineId) {
        var medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new IllegalArgumentException("Medicine not found with id: " + medicineId));
        return medicineMapper.toDto1(medicine);
    }

    @Override
    public MedicineResponse update(UUID medicineId, MedicineRequest medicineRequest) {
        var existingMedicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new IllegalArgumentException("Medicine not found with id: " + medicineId));
        medicineMapper.partialUpdate(medicineRequest, existingMedicine);
        var updatedMedicine = medicineRepository.save(existingMedicine);
        return medicineMapper.toDto1(updatedMedicine);
    }

    @Override
    public void delete(UUID medicineId) {
        var entity = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new IllegalArgumentException("Medicine not found with id: " + medicineId));
        entity.setDeleted(true);
        medicineRepository.save(entity);
    }

    private String generateMedicineCode() {
        String prefix = "MED";
        long count = medicineRepository.count() + 1;
        return String.format("%s%05d", prefix, count);
    }
}
