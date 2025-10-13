package sba.group3.clinic.appointment.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import sba.group3.clinic.clinic.entity.Room;
import sba.group3.clinic.common.entity.BaseEntity;
import sba.group3.clinic.doctor.entity.Doctor;
import sba.group3.clinic.patient.entity.Patient;

@Getter
@Setter
@Entity
@Table(name = "appointment")
public class Appointment extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id", nullable = false)
    Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id")
    Doctor doctor; // Bác sĩ được chỉ định (nếu có)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    Room room;

    @Column(name = "appointment_date", nullable = false)
    private String appointmentDate; // Ngày hẹn
    @Column(name = "appointment_time", nullable = false)
    private String appointmentTime; // Thời gian hẹn


}