package sba.group3.clinic.doctor.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.user.entity.User;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "doctor", schema = "clinic_management",
        indexes = {
                @Index(name = "idx_doctor_user_id", columnList = "user_id"),
                @Index(name = "idx_doctor_department_id", columnList = "department_id")
        })
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class Doctor extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    User user;

    /** Phòng ban / chuyên khoa chính */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "department_id")
    Department department;

    /** Các nhánh chuyên môn chi tiết hơn */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "doctor_specialty",
            schema = "clinic_management",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"),
            uniqueConstraints = @UniqueConstraint(name = "uc_doctor_specialty", columnNames = {"doctor_id", "specialty_id"})
    )
    Set<Specialty> specialties = new HashSet<>();

    /** Tên hiển thị của bác sĩ */
    @Column(name = "display_name", nullable = false, length = 255)
    String displayName;

    /** Mô tả ngắn về bác sĩ */
    @Column(name = "bio", columnDefinition = "TEXT")
    String bio;

    @Column(name = "start_date")
    Integer startDate;

    @Column(name = "avatar_url", length = 500)
    String avatarUrl;

    @Column(name = "contact_phone", length = 20)
    String contactPhone;

    @Column(name = "contact_email")
    String contactEmail;

    @OneToMany(mappedBy = "doctor", orphanRemoval = true)
    private Set<Qualification> qualifications = new LinkedHashSet<>();

}
