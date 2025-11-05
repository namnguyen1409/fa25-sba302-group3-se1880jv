package sba.group3.backendmvc.service.examination.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.response.examination.PrescriptionItemRequest;
import sba.group3.backendmvc.dto.response.examination.PrescriptionItemResponse;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.PrescriptionItem;
import sba.group3.backendmvc.mapper.examination.PrescriptionItemMapper;
import sba.group3.backendmvc.repository.examination.PrescriptionItemRepository;
import sba.group3.backendmvc.repository.examination.PrescriptionRepository;
import sba.group3.backendmvc.repository.pharmacy.MedicineRepository;
import sba.group3.backendmvc.service.examination.PrescriptionItemService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionItemServiceImpl implements PrescriptionItemService {
    private final PrescriptionItemRepository prescriptionItemRepository;
    private final PrescriptionItemMapper prescriptionItemMapper;
    private final PrescriptionRepository prescriptionRepository;
    private final MedicineRepository medicineRepository;

    @Override
    public Page<PrescriptionItemResponse> filter(SearchFilter filter) {
        return prescriptionItemRepository.search(filter).map(prescriptionItemMapper::toDto1);
    }

    @Override
    public PrescriptionItemResponse create(PrescriptionItemRequest request) {
        PrescriptionItem prescriptionItem = prescriptionItemMapper.toEntity(request);
        return prescriptionItemMapper.toDto1(prescriptionItemRepository.save(prescriptionItem));
    }

    @Override
    public PrescriptionItemResponse update(String id, PrescriptionItemRequest request) {
        PrescriptionItem prescriptionItem = prescriptionItemRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("PrescriptionItem with id " + id + " not found."));

        prescriptionItemMapper.partialUpdate(request, prescriptionItem);
        if(request.prescriptionId() != null){
            prescriptionItem.setPrescription(prescriptionRepository.findById(request.prescriptionId())
                    .orElseThrow(() -> new IllegalArgumentException("Prescription not found with id " + request.prescriptionId())));
        }
        if(request.medicineId() != null){
            prescriptionItem.setMedicine(medicineRepository.findById(request.medicineId())
                    .orElseThrow(() -> new IllegalArgumentException("Medicine not found with id " + request.medicineId())));
        }

        PrescriptionItem saved = prescriptionItemRepository.save(prescriptionItem);

        return prescriptionItemMapper.toDto1(saved);
    }

    @Override
    public void delete(String id) {
        PrescriptionItem prescriptionItem = prescriptionItemRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new IllegalArgumentException("PrescriptionItem with id " + id + " not found."));
        prescriptionItem.setDeleted(true);
        prescriptionItemRepository.save(prescriptionItem);
    }
}
