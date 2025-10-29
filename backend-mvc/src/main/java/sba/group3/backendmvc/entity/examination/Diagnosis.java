package sba.group3.backendmvc.entity.examination;

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
@Table(name = "diagnosis", schema = "examination")
public class Diagnosis extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "examination_id")
    Examination examination;

    @Column(name = "icd_code", length = 10)
    String icdCode;

    @Column(name = "disease_name", nullable = false, length = 255)
    String diseaseName;

    @Column(name = "note", columnDefinition = "TEXT")
    String note;
}