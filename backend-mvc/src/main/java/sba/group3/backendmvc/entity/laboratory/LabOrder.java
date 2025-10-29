package sba.group3.backendmvc.entity.laboratory;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.patient.Patient;
import sba.group3.backendmvc.entity.staff.Staff;

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
@Table(name = "lab_order", schema = "laboratory")
public class LabOrder extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "requested_by")
    Staff requestedBy; // bác sĩ chỉ định

    @ManyToOne
    @JoinColumn(name = "examination_id")
    Examination examination; // lần khám liên quan

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    LabStatus status = LabStatus.PENDING;

    @Column(name = "order_code", nullable = false, unique = true, length = 30)
    String orderCode;

    @OneToMany(mappedBy = "labOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<LabTestResult> results = new HashSet<>();
}