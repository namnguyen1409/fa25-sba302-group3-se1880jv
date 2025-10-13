package sba.group3.clinic.doctor.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.hibernate.proxy.HibernateProxy;
import sba.group3.clinic.common.entity.BaseEntity;

import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Chuy khoa nhỏ hơn, cụ thể hơn trong một phòng ban, ví dụ trong phòng ban Nội có các chuyên khoa như: Tim mạch, Tiêu hóa, Hô hấp...
 * <a href="https://en-wikipedia-org.translate.goog/wiki/Medical_specialty?_x_tr_sl=en&_x_tr_tl=vi&_x_tr_hl=vi&_x_tr_pto=tc">Tham khảo thêm</a>
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "specialty", schema = "clinic_management",
        uniqueConstraints = @UniqueConstraint(name = "uc_specialty_name", columnNames = "name"))
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class Specialty extends BaseEntity {

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @ManyToMany(mappedBy = "specialties", fetch = FetchType.LAZY)
    Set<Doctor> doctors = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Specialty specialty = (Specialty) o;
        return getId() != null && Objects.equals(getId(), specialty.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}
