package sba.group3.backendmvc.entity.appointment;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.entity.staff.Staff;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "queue_ticket", schema = "appointment")
public class QueueTicket extends BaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "appointment_id", nullable = false, unique = true)
    Appointment appointment;

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    Staff assignedDoctor;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id", nullable = false)
    Room assignedRoom;

    @Column(name = "queue_number", nullable = false, unique = true, length = 10)
    String queueNumber;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    QueueStatus status = QueueStatus.WAITING;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "priority", length = 20)
    QueuePriority priority = QueuePriority.NORMAL;

    @OneToOne
    @JoinColumn(name = "examination_id")
    Examination examination;
}
