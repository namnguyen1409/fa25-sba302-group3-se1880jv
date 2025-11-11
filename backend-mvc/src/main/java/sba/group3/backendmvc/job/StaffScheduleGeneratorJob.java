package sba.group3.backendmvc.job;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.service.staff.StaffScheduleService;

@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Component
public class StaffScheduleGeneratorJob {


    private final StaffRepository staffRepository;
    private final StaffScheduleService staffScheduleService;

    @Scheduled(cron = "0 10 0 * * *", zone = "Asia/Ho_Chi_Minh")
    public void generateNext30Days() {
        staffRepository.findAll().forEach(s -> staffScheduleService.generate(s.getId(), 30));
    }
}
