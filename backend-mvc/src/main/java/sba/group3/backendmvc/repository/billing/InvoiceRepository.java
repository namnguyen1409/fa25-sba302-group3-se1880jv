package sba.group3.backendmvc.repository.billing;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sba.group3.backendmvc.controller.report.ReportController;
import sba.group3.backendmvc.entity.billing.Invoice;
import sba.group3.backendmvc.repository.BaseRepository;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
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

    long countByIssueDate(LocalDateTime issueDate);

    long countByCreatedDateBetween(Instant createdDateAfter, Instant createdDateBefore);

    @Query("""
                select coalesce(sum(i.totalAmount), 0)
                from Invoice i
                where date(i.issueDate) = :day
            """)
    BigDecimal sumRevenueByIssueDate(@Param("day") LocalDate today);

    long countByIssueDateBetween(LocalDateTime issueDateAfter, LocalDateTime issueDateBefore);

}