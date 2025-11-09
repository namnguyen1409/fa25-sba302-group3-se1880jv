package sba.group3.backendmvc.repository.patient;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sba.group3.backendmvc.controller.report.ReportController;
import sba.group3.backendmvc.entity.patient.Patient;
import sba.group3.backendmvc.repository.BaseRepository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface PatientRepository extends BaseRepository<Patient, UUID> {
    Boolean existsByPhoneOrEmail(String phone, String email);

    boolean existsByUser_Id(UUID userId);



}