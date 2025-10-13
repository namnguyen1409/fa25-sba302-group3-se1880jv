package sba.group3.clinic.doctor.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import sba.group3.clinic.clinic.entity.Room;
import sba.group3.clinic.common.entity.BaseEntity;

import java.util.LinkedHashSet;
import java.util.Set;

/**
 * Phòng ban, chuyên khoa lớn như: Nội, Ngoại, Nhi, Sản, Răng Hàm Mặt.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "department", schema = "clinic_management",
        uniqueConstraints = @UniqueConstraint(name = "uc_department_name", columnNames = "name"))
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;


    @OneToMany(mappedBy = "department", fetch = FetchType.LAZY)
    Set<Doctor> doctors = new LinkedHashSet<>();

    @OneToMany(mappedBy = "department", orphanRemoval = true)
    Set<Specialty> specialties = new LinkedHashSet<>();

    @OneToMany(mappedBy = "department", orphanRemoval = true)
    private Set<Room> rooms = new LinkedHashSet<>();

}
