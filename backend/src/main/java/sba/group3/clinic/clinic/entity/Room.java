package sba.group3.clinic.clinic.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.doctor.entity.Department;
import sba.group3.clinic.user.entity.User;

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room extends BaseEntity {
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location")
    private String location;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "department_id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private User doctor;

    @ManyToOne
    @JoinColumn(name = "nurse_id")
    private User nurse;

    @Column
    private String description;

    @Column(nullable = false)
    boolean allowOnlineBooking = false;
}