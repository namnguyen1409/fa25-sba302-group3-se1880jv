package sba.group3.backendmvc.entity.examination;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.pharmacy.Medicine;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "prescription_item", schema = "examination")
public class PrescriptionItem extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "prescription_id")
    Prescription prescription;

    @ManyToOne(optional = false)
    @JoinColumn(name = "medicine_id")
    Medicine medicine;

    @Column(name = "dosage", length = 50)
    String dosage;

    @Column(name = "frequency", length = 50)
    String frequency;

    @Column(name = "duration", length = 50)
    String duration;

    @Column(name = "instruction", columnDefinition = "TEXT")
    String instruction;
}