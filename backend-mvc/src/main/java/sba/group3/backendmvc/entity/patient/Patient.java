package sba.group3.backendmvc.entity.patient;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.user.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(
        name = "patient",
        schema = "patient"
)
public class Patient extends BaseEntity {
    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

    @Column(name = "patient_code", nullable = false, unique = true, length = 20)
    String patientCode;

    @Column(name = "full_name", nullable = false, length = 150)
    String fullName;

    @Column(name = "date_of_birth")
    LocalDate dateOfBirth;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", length = 10)
    Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(name = "blood_type", length = 20)
    BloodType bloodType;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    PatientStatus status = PatientStatus.ACTIVE;

    @Column(name = "phone", length = 20)
    String phone;

    @Column(name = "email", length = 100)
    String email;

    @Column(name = "address", length = 255)
    String address;

    @Column(name = "insurance_number", length = 20)
    String insuranceNumber;

    @Column(name = "init_password", length = 255)
    private String initPassword;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<MedicalRecord> medicalRecords = new HashSet<>();



}