package sba.group3.backendmvc.entity.appointment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.entity.patient.Patient;
import sba.group3.backendmvc.entity.staff.Staff;

import java.time.LocalDateTime;

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
    @JoinColumn(name = "staff_id", nullable = false)
    Staff staff;

    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;

    @Enumerated(EnumType.STRING)
    @Column(name = "type", length = 30)
    AppointmentType type = AppointmentType.CONSULTATION;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    AppointmentStatus status = AppointmentStatus.PENDING;

    @Enumerated(EnumType.STRING)
    @Column(name = "source", length = 30)
    AppointmentSource source = AppointmentSource.SYSTEM;

    @Column(name = "scheduled_start", nullable = false)
    LocalDateTime scheduledStart;

    @Column(name = "scheduled_end")
    LocalDateTime scheduledEnd;

    @Column(name = "note", columnDefinition = "TEXT")
    String note;

    @OneToOne(mappedBy = "appointment", cascade = CascadeType.ALL)
    QueueTicket queueTicket;

}
