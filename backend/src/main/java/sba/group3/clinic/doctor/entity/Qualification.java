package sba.group3.clinic.doctor.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.proxy.HibernateProxy;
import sba.group3.clinic.common.entity.BaseEntity;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Bằng cấp, chứng chỉ hành nghề của bác sĩ như: Bác sĩ đa khoa, Bác sĩ chuyên khoa I, Bác sĩ chuyên khoa II, Thạc sĩ, Tiến sĩ, ...
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "qualification")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Qualification extends BaseEntity {
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    private Doctor doctor;

    @Column(name = "title", nullable = false, length = 150)
    private String title;

    @Column(name = "institution", length = 200)
    private String institution;

    /**
     *  date_awarded: ngày cấp bằng
     */
    @Column(name = "date_awarded")
    private LocalDate dateAwarded;

    @Column(name = "description", length = 500)
    String description;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Qualification that = (Qualification) o;
        return getId() != null && Objects.equals(getId(), that.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}