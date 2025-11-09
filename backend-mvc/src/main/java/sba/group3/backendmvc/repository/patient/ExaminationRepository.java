package sba.group3.backendmvc.repository.patient;

import org.springframework.data.jpa.repository.Query;
import sba.group3.backendmvc.controller.report.ReportController;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.ExaminationStatus;
import sba.group3.backendmvc.repository.BaseRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ExaminationRepository extends BaseRepository<Examination, UUID> {
    long countByCreatedDateBetweenAndStatus(Instant createdDateAfter, Instant createdDateBefore, ExaminationStatus status);


}