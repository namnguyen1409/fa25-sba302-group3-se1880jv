package sba.group3.backendmvc.repository.staff;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.entity.staff.StaffSchedule;
import sba.group3.backendmvc.repository.BaseRepository;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Optional;
import java.util.UUID;

public interface StaffScheduleRepository extends BaseRepository<StaffSchedule, UUID> {

    @Query("""
    SELECT ss.room FROM StaffSchedule ss
    WHERE ss.staff.id = :staffId
      AND ss.dayOfWeek = :day
      AND ss.available = true
      AND (:now <= ss.endTime)
""")
    Optional<Room> findActiveRoomForDoctor(
            @Param("staffId") UUID staffId,
            @Param("day") DayOfWeek day,
            @Param("now") LocalTime now
    );

}