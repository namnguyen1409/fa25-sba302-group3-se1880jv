package sba.group3.backendmvc.repository.examination;

import org.springframework.data.jpa.repository.Query;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.examination.ServiceOrder;
import sba.group3.backendmvc.entity.organization.RoomType;
import sba.group3.backendmvc.repository.BaseRepository;

import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ServiceOrderRepository extends BaseRepository<ServiceOrder, UUID> {
    ServiceOrder findByExamination(Examination examination);

    @Query("""
                SELECT o.room.id AS roomId, COUNT(o) AS activeOrders
                FROM ServiceOrder o
                WHERE o.room.id IN :roomIds
                  AND o.status IN ('PENDING', 'READY', 'IN_PROGRESS')
                GROUP BY o.room.id
            """)
    List<Map<String, Object>> countActiveOrdersByRoomIds(List<UUID> roomIds);

    long countByCreatedDateBetweenAndRoom_RoomTypeIn(Instant createdDateAfter, Instant createdDateBefore, List<RoomType> roomTypes);

}