package sba.group3.backendmvc.service.pharmacy.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.pharmacy.MedicineRequest;
import sba.group3.backendmvc.dto.response.pharmacy.MedicineResponse;
import sba.group3.backendmvc.mapper.pharmacy.MedicineMapper;
import sba.group3.backendmvc.repository.pharmacy.MedicineRepository;
import sba.group3.backendmvc.service.pharmacy.MedicineService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class MedicineServiceImpl implements MedicineService {
    private final MedicineRepository medicineRepository;
    private final MedicineMapper medicineMapper;

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
