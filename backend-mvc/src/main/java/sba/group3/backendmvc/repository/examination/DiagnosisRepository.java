package sba.group3.backendmvc.repository.examination;

import sba.group3.backendmvc.entity.examination.Diagnosis;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface DiagnosisRepository extends BaseRepository<Diagnosis, UUID> {
    Diagnosis findByExamination(Examination examination);
}