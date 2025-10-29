package sba.group3.backendmvc.entity.patient;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "allergy", schema = "patient")
public class Allergy extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    Patient patient;

    @Column(name = "substance", nullable = false, length = 100)
    String substance;

    @Column(name = "reaction", columnDefinition = "TEXT")
    String reaction;

    @Column(name = "severity", length = 50)
    String severity;
}