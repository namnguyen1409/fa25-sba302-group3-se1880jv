package sba.group3.backendmvc.entity.appointment;

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
@Table(name = "queue_ticket", schema = "appointment")
public class QueueTicket extends BaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "appointment_id", nullable = false, unique = true)
    Appointment appointment;

    @Column(name = "queue_number", nullable = false, unique = true, length = 10)
    String queueNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20)
    QueueStatus status = QueueStatus.WAITING;

    @Enumerated(EnumType.STRING)
    @Column(name = "priority", length = 20)
    QueuePriority priority = QueuePriority.NORMAL;

    @Column(name = "counter", length = 50)
    String counter; // quầy hoặc phòng đang phục vụ
}