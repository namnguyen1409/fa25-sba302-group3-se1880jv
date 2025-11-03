package sba.group3.backendmvc.dto.response.patient;

import sba.group3.backendmvc.entity.patient.BloodType;
import sba.group3.backendmvc.entity.patient.Gender;
import sba.group3.backendmvc.entity.patient.PatientStatus;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.patient.Patient}
 */
public record PatientResponse(UUID id, String patientCode, String fullName, LocalDate dateOfBirth, Gender gender,
                              BloodType bloodType, PatientStatus status, String phone, String email, String address,
                              String insuranceNumber, String initPassword) implements Serializable {
}