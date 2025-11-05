package sba.group3.backendmvc.entity.staff;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.organization.Room;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(
        name = "staff_schedule",
        schema = "medical_staff_management"
)
public class StaffSchedule extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "staff_id")
    Staff staff;

    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    DayOfWeek dayOfWeek;

    @Column(name = "start_time", nullable = false)
    LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    LocalTime endTime;

    @Column(name = "available", nullable = false)
    boolean available;

    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id")
    Room room;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        StaffSchedule that = (StaffSchedule) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}