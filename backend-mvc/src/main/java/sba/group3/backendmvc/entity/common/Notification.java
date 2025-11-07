package sba.group3.backendmvc.entity.common;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.user.User;

@Getter
@Setter
@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Table(
        name = "notification",
        schema = "common"
)
public class Notification extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Enumerated(EnumType.STRING)
    private NotificationType type; // ORDER_NEW, QUEUE_NEW, LAB_NEW,...

    @Column(nullable = false)
    private String title;

    @Column
    private String message;

    @Column
    private String referenceId;

    @Enumerated(EnumType.STRING)
    private NotificationStatus status; // UNREAD, READ

    @Column
    private String topic;

    public enum NotificationType {
        QUEUE_NEW,
        ORDER_NEW,
        LAB_ORDER_NEW,
        PAYMENT_NEW,
        APPOINTMENT_NEW
    }

    public enum NotificationStatus {
        READ,
        UNREAD
    }
}
