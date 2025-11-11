package sba.group3.backendmvc.entity.examination;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.entity.staff.Staff;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "service_order", schema = "examination")
public class ServiceOrder extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "examination_id")
    Examination examination;

    @Column(name = "order_code", nullable = false, unique = true, length = 20)
    String orderCode;

    @ManyToOne
    @JoinColumn(name = "room_id")
    Room room;  // phòng thực hiện service

    @ManyToOne
    @JoinColumn(name = "assigned_staff_id")
    Staff assignedStaff;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    ServiceOrderStatus status = ServiceOrderStatus.PENDING;

    @Builder.Default
    @OneToMany(mappedBy = "serviceOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ServiceOrderItem> items = new HashSet<>();
}