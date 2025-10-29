package sba.group3.backendmvc.entity.patient;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.examination.Examination;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(
        name = "medical_record",
        schema = "patient"
)
public class MedicalRecord extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id")
    Patient patient;

    @OneToOne
    @JoinColumn(name = "examination_id")
    Examination examination;

    @Column(name = "record_code", nullable = false, unique = true, length = 20)
    String recordCode;

    @Column(name = "diagnosis", columnDefinition = "TEXT")
    String diagnosis;

    @Column(name = "treatment", columnDefinition = "TEXT")
    String treatment;

}