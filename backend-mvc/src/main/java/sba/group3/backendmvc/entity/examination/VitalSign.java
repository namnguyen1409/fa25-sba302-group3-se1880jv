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
@Table(name = "vital_sign", schema = "examination")
public class VitalSign extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "examination_id")
    Examination examination;

    @Column(name = "temperature")
    Double temperature;

    @Column(name = "blood_pressure", length = 20)
    String bloodPressure;
}