package sba.group3.clinic.patient.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sba.group3.clinic.common.entity.BaseEntity;

@Getter
@Setter
@Entity
@Table(name = "allergy")
public class Allergy extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    Patient patient;

    @Column(length = 100, nullable = false)
    String allergen; // tác nhân dị ứng

    @Column(length = 500)
    String reaction; // phản ứng

    @Column(length = 100)
    String severity; // độ nghiêm trọng
}