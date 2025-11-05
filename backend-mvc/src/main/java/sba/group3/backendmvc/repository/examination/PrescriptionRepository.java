package sba.group3.backendmvc.repository.examination;

import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.Prescription;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface PrescriptionRepository extends BaseRepository<Prescription, UUID> {
    Prescription findByExamination(Examination examination);
}