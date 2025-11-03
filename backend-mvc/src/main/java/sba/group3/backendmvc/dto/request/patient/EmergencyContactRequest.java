package sba.group3.backendmvc.dto.request.patient;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;

/**
 * DTO for {@link sba.group3.backendmvc.entity.patient.EmergencyContact}
 */
public record EmergencyContactRequest(@NotBlank(message = "This field cannot be blank") String fullName, String relationship, @Pattern(message = "Invalid phone number", regexp = "^(?:\\+84|0)(?:3[2-9]|5[2689]|7[06-9]|8[1-9]|9\\d)\\d{7}$") String phone) implements Serializable {
  }