package sba.group3.backendmvc.service.examination.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.DiagnosisRequest;
import sba.group3.backendmvc.dto.response.examination.DiagnosisResponse;
import sba.group3.backendmvc.entity.examination.Diagnosis;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.ServiceCatalog;
import sba.group3.backendmvc.mapper.examination.DiagnosisMapper;
import sba.group3.backendmvc.repository.examination.DiagnosisRepository;
import sba.group3.backendmvc.repository.patient.ExaminationRepository;
import sba.group3.backendmvc.service.examination.DiagnosisService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class DiagnosisServiceImpl implements DiagnosisService {
    private final DiagnosisRepository diagnosisRepository;
    private final DiagnosisMapper diagnosisMapper;
    private final ExaminationRepository examinationRepository;

    @Override
    public Page<DiagnosisResponse> filter(SearchFilter filter) {
        return diagnosisRepository.search(filter).map(diagnosisMapper::toDto);
    }

    @Override
    public DiagnosisResponse create(DiagnosisRequest request) {
        Diagnosis diagnosis = diagnosisMapper.toEntity(request);
        return diagnosisMapper.toDto(diagnosisRepository.save(diagnosis));
    }

    @Override
    public DiagnosisResponse update(String id, DiagnosisRequest request) {
        Diagnosis diagnosis = diagnosisRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("Diagnosis with id " + id + " not found."));

        diagnosisMapper.partialUpdate(request, diagnosis);

        if(request.examinationId() != null) {
            diagnosis.setExamination(examinationRepository.findById(request.examinationId())
                    .orElseThrow(() -> new IllegalArgumentException("Examination not found with id " + request.examinationId())));
        }

        Diagnosis saved = diagnosisRepository.save(diagnosis);


        return diagnosisMapper.toDto(saved);
    }

    @Override
    public void delete(String id) {
        Diagnosis diagnosis = diagnosisRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("Diagnosis with id " + id + " not found."));

        diagnosis.setDeleted(true);
        diagnosisRepository.save(diagnosis);
    }

    @Override
    public DiagnosisResponse searchByExaminationId(String examinationId) {
        Examination examination = examinationRepository.findById(UUID.fromString(examinationId))
                .orElseThrow(() -> new RuntimeException("Examination not found with id: " + examinationId));

        Diagnosis diagnosis = diagnosisRepository.findByExamination(examination);
        if(diagnosis == null) {
            throw new IllegalArgumentException("Diagnosis for Examination id " + examinationId + " not found.");
        }

        return diagnosisMapper.toDto(diagnosis);
    }
}
