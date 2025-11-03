package sba.group3.backendmvc.repository.patient;

import sba.group3.backendmvc.entity.patient.MedicalRecord;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface MedicalRecordRepository extends BaseRepository<MedicalRecord, UUID> {
}