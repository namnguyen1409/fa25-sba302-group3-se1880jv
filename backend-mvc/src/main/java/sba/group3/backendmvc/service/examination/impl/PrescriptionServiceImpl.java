package sba.group3.backendmvc.service.examination.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.PrescriptionRequest;
import sba.group3.backendmvc.dto.response.examination.PrescriptionResponse;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.Prescription;
import sba.group3.backendmvc.mapper.examination.PrescriptionMapper;
import sba.group3.backendmvc.repository.examination.PrescriptionRepository;
import sba.group3.backendmvc.repository.patient.ExaminationRepository;
import sba.group3.backendmvc.service.examination.PrescriptionService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PrescriptionServiceImpl implements PrescriptionService {
    private final PrescriptionRepository prescriptionRepository;
    private final PrescriptionMapper prescriptionMapper;
    private final ExaminationRepository examinationRepository;

    @Override
    public Page<PrescriptionResponse> filter(SearchFilter filter) {
        return prescriptionRepository.search(filter).map(prescriptionMapper::toDto1);
    }

    @Override
    public PrescriptionResponse create(PrescriptionRequest request) {
        Prescription prescription = prescriptionMapper.toEntity(request);
        return prescriptionMapper.toDto1(prescriptionRepository.save(prescription));
    }

    @Override
    public PrescriptionResponse update(String id, PrescriptionRequest request) {
        Prescription prescription = prescriptionRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("Prescription with id " + id + " not found."));

        prescriptionMapper.partialUpdate(request, prescription);
        if(request.examinationId() != null) {
            prescription.setExamination(examinationRepository.findById(UUID.fromString(id))
                    .orElseThrow(()->new IllegalArgumentException("Examination not found with id " + request.examinationId())));
        }

        Prescription saved = prescriptionRepository.save(prescription);
        return prescriptionMapper.toDto1(saved);
    }


    @Override
    public void delete(String id) {
        Prescription prescription = prescriptionRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("Prescription with id " + id + " not found."));
        prescription.setDeleted(true);
        prescriptionRepository.save(prescription);
    }

    @Override
    public PrescriptionResponse getPrescriptionByExaminationId(String examinationId) {
        Examination examination = examinationRepository.findById(UUID.fromString(examinationId)).orElseThrow(()->
                new IllegalArgumentException("Examination with id " + examinationId + " not found."));
        Prescription prescription = prescriptionRepository.findByExamination((examination));
        if(prescription == null) {
            throw new IllegalArgumentException("Prescription for Examination id " + examinationId + " not found.");
        }
        return prescriptionMapper.toDto1(prescription);
    }
}
