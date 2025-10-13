package sba.group3.clinic.patient.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.common.entity.IcdCode;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "medical_record")
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class MedicalRecord extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(name = "title")
    private String title;

    @Column(name = "start_date")
    LocalDate startDate;

    @Column(name = "end_date")
    LocalDate endDate;

    @ManyToMany
    @JoinTable(name = "medical_record_icd",
            joinColumns = @JoinColumn(name = "record_id"),
            inverseJoinColumns = @JoinColumn(name = "icd_id"))
    Set<IcdCode> diagnoses = new LinkedHashSet<>();


    @OneToMany(mappedBy = "medicalRecord", orphanRemoval = true)
    private Set<ExaminationForm> examinationForms = new LinkedHashSet<>();

}