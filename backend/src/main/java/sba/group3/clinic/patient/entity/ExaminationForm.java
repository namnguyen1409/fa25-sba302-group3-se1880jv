package sba.group3.clinic.patient.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.common.entity.IcdCode;
import sba.group3.clinic.doctor.entity.Doctor;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "examination_form")
public class ExaminationForm extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "medical_record_id")
    private MedicalRecord medicalRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "icd_code_id")
    IcdCode diagnosisCode;

    LocalDateTime examinationDate;

    @Column(length = 2000)
    String symptoms;

    @Column(length = 2000)
    String diagnosisDetails;

    @Column(length = 2000)
    String treatmentPlan;

//    @OneToOne(mappedBy = "examinationForm", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    Prescription prescription;
}