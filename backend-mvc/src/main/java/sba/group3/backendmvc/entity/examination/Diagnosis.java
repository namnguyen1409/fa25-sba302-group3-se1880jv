package sba.group3.backendmvc.entity.examination;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.proxy.HibernateProxy;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.common.IcdCode;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "diagnosis", schema = "examination")
public class Diagnosis extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "examination_id")
    Examination examination;

    @ManyToOne
    @JoinColumn(name = "icd_code_id")
    private IcdCode icdCode;

    @Column(name = "note", columnDefinition = "TEXT")
    String note;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Diagnosis diagnosis = (Diagnosis) o;
        return getId() != null && Objects.equals(getId(), diagnosis.getId());
    }

    @Override
    public final int hashCode() {
        return this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode() : getClass().hashCode();
    }
}