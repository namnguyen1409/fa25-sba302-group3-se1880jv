package sba.group3.backendmvc.entity.staff;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.organization.Department;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(
        name = "specialty",
        schema = "medical_staff_management"
)
public class Specialty extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    Department department;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "description")
    String description;
}