package sba.group3.clinic.patient.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.user.entity.User;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "patient", schema = "patient_management")
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Patient extends BaseEntity {
    @OneToOne(optional = false, orphanRemoval = true)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

    @Column(name = "patient_code", nullable = false, unique = true, length = 20)
    String patientCode;

    @Column(name = "insurance_number", length = 20)
    String insuranceNumber;

    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    Set<MedicalRecord> medicalRecords = new HashSet<>();

}