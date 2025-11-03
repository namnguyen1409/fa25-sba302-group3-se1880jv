package sba.group3.backendmvc.repository.patient;

import sba.group3.backendmvc.entity.patient.Patient;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.UUID;

public interface PatientRepository extends BaseRepository<Patient, UUID> {
    Boolean existsByPhoneOrEmail(String phone, String email);
}