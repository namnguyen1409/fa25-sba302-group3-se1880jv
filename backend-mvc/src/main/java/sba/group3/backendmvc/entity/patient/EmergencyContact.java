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
@Table(name = "emergency_contact", schema = "patient")
public class EmergencyContact extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    Patient patient;

    @Column(name = "full_name", nullable = false, length = 150)
    String fullName;

    @Column(name = "relationship", length = 50)
    String relationship;

    @Column(name = "phone", nullable = false, length = 20)
    String phone;

}