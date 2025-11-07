package sba.group3.backendmvc.service.examination.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.examination.ExaminationRequest;
import sba.group3.backendmvc.dto.response.examination.ExaminationResponse;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.ExaminationStatus;
import sba.group3.backendmvc.entity.examination.ExaminationType;
import sba.group3.backendmvc.mapper.examination.ExaminationMapper;
import sba.group3.backendmvc.repository.patient.ExaminationRepository;
import sba.group3.backendmvc.repository.patient.PatientRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.service.examination.ExaminationService;

import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ExaminationServiceImpl implements ExaminationService {
    private final ExaminationRepository examinationRepository;
    private final ExaminationMapper examinationMapper;
    private final PatientRepository patientRepository;
    private final StaffRepository staffRepository;
    
    @Override
    public Page<ExaminationResponse> filter(SearchFilter filter) {
        return examinationRepository.search(filter).map(examinationMapper::toDto1);
    }


    @Override
    public ExaminationResponse create(ExaminationRequest request) {
        Examination examination = examinationMapper.toEntity(request);
        return examinationMapper.toDto1(examinationRepository.save(examination));
    }

    @Override
    public ExaminationResponse getExaminationDetail(String id) {
        Examination examination = examinationRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("Examination with id " + id + " not found."));

        return examinationMapper.toDto1(examination);
    }

    @Override
    public ExaminationResponse update(String id, ExaminationRequest request) {
        Examination examination = examinationRepository.findById(UUID.fromString(id)).orElseThrow(()->
                new IllegalArgumentException("Examination with id " + id + " not found."));

        examinationMapper.partialUpdate(request, examination);

        if (request.patientId() != null) {
            examination.setPatient(patientRepository.findById(request.patientId())
                    .orElseThrow(() -> new IllegalArgumentException("Patient not found with id " + request.patientId())));
        }

        if (request.staffId() != null) {
            examination.setStaff(staffRepository.findById(request.staffId())
                    .orElseThrow(() -> new IllegalArgumentException("Staff not found with id " + request.staffId())));
        }

        Examination saved = examinationRepository.save(examination);


        return examinationMapper.toDto1(saved);
    }

    @Override
    public void delete(String id) {
        Examination examination = examinationRepository.findById(UUID.fromString(id)).orElseThrow(
                () -> new IllegalArgumentException("Examination with id " + id + " not found."));
        examination.setDeleted(true);
        examinationRepository.save(examination);
    }

    @Override
    public Examination createFromQueueTicket(QueueTicket ticket) {
        Examination examination = Examination.builder()
                .patient(ticket.getAppointment().getPatient())
                .staff(ticket.getAssignedDoctor())
                .type(ExaminationType.GENERAL)
                .status(ExaminationStatus.ONGOING)
                .build();
        return examinationRepository.save(examination);
    }
}
