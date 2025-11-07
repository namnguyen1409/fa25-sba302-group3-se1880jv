package sba.group3.backendmvc.repository.staff;

import sba.group3.backendmvc.entity.staff.StaffScheduleTemplate;
import sba.group3.backendmvc.repository.BaseRepository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

public interface StaffScheduleTemplateRepository extends BaseRepository<StaffScheduleTemplate, UUID> {
    List<StaffScheduleTemplate> findByStaff_IdAndActiveTrue(UUID staffId);

    boolean existsByStaffIdAndDayOfWeekAndDeletedFalse(UUID staffId, DayOfWeek dayOfWeek);

    List<StaffScheduleTemplate> findAllByStaffIdAndDayOfWeekAndDeletedFalse(UUID staffId, DayOfWeek dayOfWeek);

    List<StaffScheduleTemplate>  findAllByRoomIdAndDayOfWeekAndDeletedFalse(UUID roomId, DayOfWeek dayOfWeek);
}