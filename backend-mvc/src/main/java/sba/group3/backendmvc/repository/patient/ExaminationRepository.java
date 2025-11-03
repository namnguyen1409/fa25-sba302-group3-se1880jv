package sba.group3.backendmvc.repository.patient;

import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface ExaminationRepository extends BaseRepository<Examination, UUID> {
}