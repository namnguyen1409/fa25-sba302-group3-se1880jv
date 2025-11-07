package sba.group3.backendmvc.repository.staff;

import org.springframework.data.jpa.repository.Query;
import sba.group3.backendmvc.entity.staff.Staff;
import sba.group3.backendmvc.entity.staff.StaffType;
import sba.group3.backendmvc.repository.BaseRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

public interface StaffRepository extends BaseRepository<Staff, UUID> {
    boolean existsByUser_Id(UUID userId);

    List<Staff> findBySpecialty_IdAndStaffType(UUID specialtyId, StaffType staffType);

    @Query("SELECT s FROM Staff s JOIN s.staffSchedules sch " +
            "WHERE s.specialty.id = :specialtyId " +
            "AND s.staffType = 'DOCTOR' " +
            "AND sch.date = :day " +
            "AND sch.startTime <= :time " +
            "AND sch.endTime >= :time")
    List<Staff> findAvailableDoctors(UUID specialtyId, DayOfWeek day, LocalTime time);

    @Query("""
    SELECT s FROM Staff s
    JOIN s.staffSchedules sch
    WHERE s.specialty.id = :specialtyId
      AND s.staffType = 'DOCTOR'
      AND sch.date = :day
""")
    List<Staff> findDoctorsScheduledToday(UUID specialtyId, DayOfWeek day);

    @Query("""
    SELECT CASE WHEN sch.endTime >= :time THEN true ELSE false 
    END
    FROM StaffSchedule sch
    WHERE sch.staff.id = :staffId 
      AND sch.date = :day
""")
    Boolean isDoctorStillWorkingToday(UUID staffId, DayOfWeek day, LocalTime time);
}