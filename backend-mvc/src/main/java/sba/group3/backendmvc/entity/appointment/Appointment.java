package sba.group3.backendmvc.entity.appointment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.patient.Patient;
import sba.group3.backendmvc.entity.staff.Specialty;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "appointment", schema = "appointment")
public class Appointment extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    Patient patient;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialty_id", nullable = false)
    Specialty specialty;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 30)
    AppointmentType type = AppointmentType.CONSULTATION;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    AppointmentStatus status = AppointmentStatus.PENDING;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "source", length = 30)
    AppointmentSource source = AppointmentSource.WALK_IN;

    @Column(name = "note", columnDefinition = "TEXT")
    String note;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    QueueTicket queueTicket;
}
