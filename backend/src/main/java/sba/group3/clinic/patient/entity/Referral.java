package sba.group3.clinic.patient.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.doctor.entity.Department;
import sba.group3.clinic.doctor.entity.Doctor;

@Getter
@Setter
@Entity
@Table(name = "referral")
public class Referral extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_doctor_id")
    Doctor fromDoctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_department_id")
    Department toDepartment;

    @Column(name= "reason", length = 500)
    String reason;

}