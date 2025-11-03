package sba.group3.backendmvc.dto.request.patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import sba.group3.backendmvc.entity.patient.BloodType;
import sba.group3.backendmvc.entity.patient.Gender;
import sba.group3.backendmvc.entity.patient.PatientStatus;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * DTO for {@link sba.group3.backendmvc.entity.patient.Patient}
 */
public record PatientRequest(@NotBlank(message = "This field cannot be blank") String fullName, LocalDate dateOfBirth,
                             Gender gender, BloodType bloodType, PatientStatus status,
                             @Pattern(message = "Invalid phone number ", regexp = "^(?:\\+84|0)(?:3[2-9]|5[2689]|7[06-9]|8[1-9]|9\\d)\\d{7}$") String phone,
                             String email, @NotBlank(message = "This field cannot blank") String address,
                             String insuranceNumber) implements Serializable {
}