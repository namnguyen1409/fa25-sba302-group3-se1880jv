package sba.group3.backendmvc.service.examination.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.VitalSignRequest;
import sba.group3.backendmvc.dto.response.examination.VitalSignResponse;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.VitalSign;
import sba.group3.backendmvc.mapper.examination.VitalSignMapper;
import sba.group3.backendmvc.repository.examination.VitalSignRepository;
import sba.group3.backendmvc.repository.patient.ExaminationRepository;
import sba.group3.backendmvc.service.examination.VitalSignService;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class VitalSignServiceImpl implements VitalSignService {
    private final VitalSignRepository vitalSignRepository;
    private final VitalSignMapper vitalSignMapper;
    private final ExaminationRepository examinationRepository;

    @Override
    public Page<VitalSignResponse> filter(SearchFilter filter) {
        return vitalSignRepository.search(filter).map(vitalSignMapper::toDto1);
    }

    @Override
    public VitalSignResponse create(VitalSignRequest request) {
        VitalSign vitalSign = vitalSignMapper.toEntity(request);
        return vitalSignMapper.toDto1(vitalSignRepository.save(vitalSign));
    }

    @Override
    public VitalSignResponse update(String id, VitalSignRequest request) {
        VitalSign vitalSign = vitalSignRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("VitalSign with id " + id + " not found."));

        vitalSignMapper.partialUpdate(request, vitalSign);

        if( request.examinationId() != null){
            vitalSign.setExamination(examinationRepository.findById(request.examinationId())
                    .orElseThrow(() -> new IllegalArgumentException("Examination not found with id " + request.examinationId())));
        }
        VitalSign saved = vitalSignRepository.save(vitalSign);

        return vitalSignMapper.toDto1(saved);
    }

    @Override
    public void delete(String id) {
        VitalSign vitalSign = vitalSignRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new IllegalArgumentException("VitalSign with id " + id + " not found."));
        vitalSign.setDeleted(true);
        vitalSignRepository.save(vitalSign);
    }

    @Override
    public VitalSignResponse getVitalsByExaminationId(String examinationId) {
        Examination examination = examinationRepository.findById(UUID.fromString(examinationId)).orElseThrow(()->
                new IllegalArgumentException("Examination with id " + examinationId + " not found."));
        VitalSign vitalSign = vitalSignRepository.findByExamination(((examination)));
        if(vitalSign == null){
            throw new IllegalArgumentException("VitalSign for Examination id " + examinationId + " not found.");
        }
        return vitalSignMapper.toDto1(vitalSign);
    }
}
