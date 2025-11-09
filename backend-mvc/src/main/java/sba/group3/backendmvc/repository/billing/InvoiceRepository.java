package sba.group3.backendmvc.repository.billing;

import org.springframework.data.jpa.repository.Query;
import sba.group3.backendmvc.entity.billing.Invoice;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface InvoiceRepository extends BaseRepository<Invoice, UUID> {

    @Query("""
                SELECT i.room.id AS roomId, COUNT(i) AS activeOrders
                FROM Invoice i
                WHERE i.room.id IN :roomIds
                  AND i.paid = false
                GROUP BY i.room.id
            """)
    List<Map<String, Object>> countActiveOrdersByRoomIds(List<UUID> roomIds);
}