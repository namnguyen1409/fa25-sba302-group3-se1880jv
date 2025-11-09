package sba.group3.backendmvc.repository.laboratory;

import org.springframework.data.jpa.repository.Query;
import sba.group3.backendmvc.entity.laboratory.LabOrder;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface LabOrderRepository extends BaseRepository<LabOrder, UUID> {

    @Query("""
                SELECT l.room.id AS roomId, COUNT(l) AS activeOrders
                FROM LabOrder l
                WHERE l.room.id IN :roomIds
                  AND l.status IN ('PENDING', 'IN_PROGRESS', 'COMPLETED')
                GROUP BY l.room.id
            """)
    List<Map<String, Object>> countActiveOrdersByRoomIds(List<UUID> roomIds);
}