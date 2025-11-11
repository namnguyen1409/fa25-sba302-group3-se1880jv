package sba.group3.backendmvc.repository.pharmacy;

import org.springframework.data.jpa.repository.Query;
import sba.group3.backendmvc.entity.pharmacy.DispenseRecord;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DispenseRecordRepository extends BaseRepository<DispenseRecord, UUID> {

    @Query("""
                SELECT d.room.id AS roomId, COUNT(d) AS activeOrders
                FROM DispenseRecord d
                WHERE d.room.id IN :roomIds
                  AND d.status = 'PENDING'
                GROUP BY d.room.id
            """)
    List<Map<String, Object>> countActiveOrdersByRoomIds(List<UUID> roomIds);
}