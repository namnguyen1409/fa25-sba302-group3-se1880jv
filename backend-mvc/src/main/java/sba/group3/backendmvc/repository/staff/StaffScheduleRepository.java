package sba.group3.backendmvc.repository.staff;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.entity.organization.RoomType;
import sba.group3.backendmvc.entity.staff.ScheduleStatus;
import sba.group3.backendmvc.entity.staff.StaffSchedule;
import sba.group3.backendmvc.repository.BaseRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface StaffScheduleRepository extends BaseRepository<StaffSchedule, UUID> {

    @Query("""
              select s from StaffSchedule s
              where s.staff.id = :staffId
                and s.date between :from and :to
              order by s.date, s.startTime
            """)
    List<StaffSchedule> findByStaffAndRange(@Param("staffId") UUID staffId,
                                            @Param("from") LocalDate from,
                                            @Param("to") LocalDate to);

    boolean existsByStaff_IdAndDateAndRoom_IdAndStartTimeAndEndTime(
            UUID staffId, LocalDate date, UUID roomId, LocalTime startTime, LocalTime endTime);

    @Query("""
                SELECT s.room
                FROM StaffSchedule s
                WHERE s.staff.id = :doctorId
                  AND s.date = :date
                  AND s.startTime <= :time
                  AND s.endTime >= :time
                  AND s.status in ('AVAILABLE', 'CHANGED')
            """)
    Optional<Room> findActiveRoomForDoctor(UUID doctorId, LocalDate date, LocalTime time);

    void deleteAllByStaff_IdAndDateGreaterThanEqualAndStatus(UUID staffId, LocalDate start, ScheduleStatus scheduleStatus);

    List<StaffSchedule> findAllByStaffIdAndDateAndDeletedFalse(UUID staffId, LocalDate date);

    boolean existsByStaff_IdAndDateAndStatusNot(UUID staffId, LocalDate d, ScheduleStatus scheduleStatus);

    @Query("""
            select case when count(s) > 0 then true else false end
            from StaffSchedule s
            where s.staff.id = :staffId
              and s.date = :date
              and s.status <> 'AVAILABLE'
              and s.startTime < :end
              and s.endTime > :start
            """)
    boolean existsOverlapIgnoringAvailable(
            UUID staffId, LocalDate date,
            LocalTime start, LocalTime end
    );

    @Query("""
            SELECT ss FROM StaffSchedule ss
            WHERE ss.staff.specialty.id = :specialtyId
              AND ss.date = :date
              AND ss.status in ('AVAILABLE', 'CHANGED')
              AND (:time BETWEEN ss.startTime AND ss.endTime)
            """)
    List<StaffSchedule> findActiveSchedules(UUID specialtyId, LocalDate date, LocalTime time);

    List<StaffSchedule> findAllByStaff_Specialty_IdAndDate(UUID staffSpecialtyId, LocalDate date);

    @Query("""
                SELECT ss
                FROM StaffSchedule ss
                WHERE ss.date = :date
                  AND ss.room.roomType = :roomType
                  AND ss.status IN ('AVAILABLE', 'CHANGED')
                  AND :now BETWEEN ss.startTime AND ss.endTime
                ORDER BY ss.startTime
            """)
    List<StaffSchedule> findAvailableStaffForRoomType(
            @Param("roomType") RoomType roomType,
            @Param("date") LocalDate date,
            @Param("now") LocalTime now
    );

}