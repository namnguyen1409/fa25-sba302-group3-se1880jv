package sba.group3.backendmvc.entity.examination;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.patient.Patient;
import sba.group3.backendmvc.entity.staff.Staff;

import java.time.LocalDateTime;
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
@Table(name = "examination", schema = "examination")
public class Examination extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "staff_id")
    Staff staff; // bác sĩ khám

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 50)
    ExaminationType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 32)
    ExaminationStatus status = ExaminationStatus.ONGOING;

    @Column(name = "symptom", columnDefinition = "TEXT")
    String symptom;

    @Column(name = "diagnosis_summary", columnDefinition = "TEXT")
    String diagnosisSummary;

    @Column(name = "examination_date", nullable = false)
    LocalDateTime examinationDate = LocalDateTime.now();

    @OneToOne(mappedBy = "examination", cascade = CascadeType.ALL)
    Prescription prescription;

    @OneToMany(mappedBy = "examination", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ServiceOrder> serviceOrders = new HashSet<>();

    @OneToMany(mappedBy = "examination", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<VitalSign> vitalSigns = new HashSet<>();
}