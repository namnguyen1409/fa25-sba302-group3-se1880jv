package sba.group3.backendmvc.entity.staff;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.organization.Department;
import sba.group3.backendmvc.entity.user.User;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(
        name = "staff",
        schema = "medical_staff_management"
)
public class Staff extends BaseEntity {
    @OneToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id")
    Department department;

    @ManyToOne(optional = false)
    @JoinColumn(name = "specialty_id")
    Specialty specialty;

    @Enumerated(EnumType.STRING)
    @Column(name = "staff_type", length = 32)
    StaffType staffType;

    @ManyToOne
    @JoinColumn(name = "position_id")
    Position position;

    @Column(name = "license_number", unique = true)
    String licenseNumber;

    @Column(name = "experience_years")
    Integer experienceYears;

    @Column(name = "education")
    String education;

    @Column(name = "bio", columnDefinition = "TEXT")
    String bio;

    @Column(name = "joined_date")
    LocalDate joinedDate;

    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(mappedBy = "staff", orphanRemoval = true)
    private Set<StaffSchedule> staffSchedules = new LinkedHashSet<>();

}