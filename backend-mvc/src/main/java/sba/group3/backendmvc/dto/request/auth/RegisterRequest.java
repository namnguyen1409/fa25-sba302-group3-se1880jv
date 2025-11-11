package sba.group3.backendmvc.dto.request.auth;

import lombok.*;
import sba.group3.backendmvc.dto.response.common.AddressResponse;
import sba.group3.backendmvc.entity.patient.Gender;

import java.time.LocalDate;

@Data
@Getter
@Setter
@ToString
@Builder
public class RegisterRequest {
    String username;
    String fullName;
    String email;
    String password;
    String phone;
    LocalDate dob;
    Gender gender;
    AddressResponse address;
}
