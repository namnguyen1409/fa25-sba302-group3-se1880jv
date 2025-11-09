package sba.group3.backendmvc.repository.appointment;

import org.springframework.data.jpa.repository.Query;
import sba.group3.backendmvc.entity.appointment.QueueStatus;
import sba.group3.backendmvc.entity.appointment.QueueTicket;
import sba.group3.backendmvc.repository.BaseRepository;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface QueueTicketRepository extends BaseRepository<QueueTicket, UUID> {
    @Query("""
        SELECT COUNT(q)
        FROM QueueTicket q
        WHERE q.assignedDoctor.id = :doctorId
          AND FUNCTION('DATE', q.createdDate) = CURRENT_DATE
          AND q.status = 'WAITING'
    """)
    long countWaitingTodayByDoctor(UUID doctorId);

    @Query("""
        SELECT COUNT(q)
        FROM QueueTicket q
        WHERE q.assignedDoctor.id = :doctorId
          AND FUNCTION('DATE', q.createdDate) = CURRENT_DATE
    """)
    long countTotalTodayByDoctor(UUID doctorId);


    List<QueueTicket> findAllByAssignedDoctor_IdAndCreatedDateBetween(UUID assignedDoctorId, Instant createdDateAfter, Instant createdDateBefore);


    long countByCreatedDateBetweenAndStatus(Instant createdDate, Instant createdDate2, QueueStatus status);

    long countByCreatedDateBetween(Instant createdDateAfter, Instant createdDateBefore);


}