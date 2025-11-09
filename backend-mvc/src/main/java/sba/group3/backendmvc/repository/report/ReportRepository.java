package sba.group3.backendmvc.repository.report;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Repository
public class ReportRepository {

    @PersistenceContext
    private EntityManager em;

    /** Bệnh nhân/hàng chờ theo giờ trong khoảng [start, end) */
    public List<Object[]> patientByHour(Instant start, Instant end) {
        return em.createQuery("""
            select hour(q.createdDate), count(q)
            from sba.group3.backendmvc.entity.appointment.QueueTicket q
            where q.createdDate >= :start and q.createdDate < :end
            group by hour(q.createdDate)
            order by hour(q.createdDate)
        """, Object[].class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    /** Bệnh nhân theo ngày trong khoảng [start, end) */
    public List<Object[]> patientByDay(Instant start, Instant end) {
        return em.createQuery("""
            select year(q.createdDate), month(q.createdDate), day(q.createdDate), count(q)
            from sba.group3.backendmvc.entity.appointment.QueueTicket q
            where q.createdDate >= :start and q.createdDate < :end
            group by year(q.createdDate), month(q.createdDate), day(q.createdDate)
            order by year(q.createdDate), month(q.createdDate), day(q.createdDate)
        """, Object[].class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    /** Doanh thu theo ngày (sum totalAmount), dùng issueDate nếu có */
    public List<Object[]> revenueDaily(Instant start, Instant end) {
        return em.createQuery("""
            select year(i.createdDate), month(i.createdDate), day(i.createdDate), coalesce(sum(i.totalAmount), 0)
            from sba.group3.backendmvc.entity.billing.Invoice i
            where i.createdDate >= :start and i.createdDate < :end
            group by year(i.createdDate), month(i.createdDate), day(i.createdDate)
            order by year(i.createdDate), month(i.createdDate), day(i.createdDate)
        """, Object[].class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    /** Tổng doanh thu (sum totalAmount) trong khoảng [start, end) */
    public BigDecimal sumRevenueBetween(Instant start, Instant end) {
        BigDecimal res = em.createQuery("""
            select coalesce(sum(i.totalAmount), 0)
            from sba.group3.backendmvc.entity.billing.Invoice i
            where i.createdDate >= :start and i.createdDate < :end
        """, BigDecimal.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getSingleResult();
        return res == null ? BigDecimal.ZERO : res;
    }

    /** Đếm status queue hôm nay */
    public List<Object[]> queueStatusBetween(Instant start, Instant end) {
        return em.createQuery("""
            select q.status, count(q)
            from sba.group3.backendmvc.entity.appointment.QueueTicket q
            where q.createdDate >= :start and q.createdDate < :end
            group by q.status
        """, Object[].class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    /** Phân bố chuyên khoa theo số lượt khám */
    public List<Object[]> specialtyDistribution() {
        return em.createQuery("""
            select e.staff.specialty.name, count(e)
            from sba.group3.backendmvc.entity.examination.Examination e
            group by e.staff.specialty.name
            order by count(e) desc
        """, Object[].class).getResultList();
    }

    /** Workload nhân viên theo số examination trong khoảng */
    public List<Object[]> staffWorkload(Instant start, Instant end) {
        return em.createQuery("""
            select e.staff.id, e.staff.fullName, count(e)
            from sba.group3.backendmvc.entity.examination.Examination e
            where e.createdDate >= :start and e.createdDate < :end
            group by e.staff.id, e.staff.fullName
            order by count(e) desc
        """, Object[].class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    /** Tần suất sử dụng dịch vụ (dựa ServiceOrderItem) trong khoảng */
    public List<Object[]> serviceUsage(Instant start, Instant end) {
        return em.createQuery("""
            select s.name, s.category, count(soi), coalesce(sum(soi.price), 0)
            from sba.group3.backendmvc.entity.examination.ServiceOrderItem soi
              join soi.serviceOrder so
              join soi.service s
            where so.createdDate >= :start and so.createdDate < :end
            group by s.name, s.category
            order by count(soi) desc
        """, Object[].class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getResultList();
    }

    /** Đếm LabOrder trong khoảng */
    public Long countLabOrdersBetween(Instant start, Instant end) {
        return em.createQuery("""
            select count(lo)
            from sba.group3.backendmvc.entity.laboratory.LabOrder lo
            where lo.createdDate >= :start and lo.createdDate < :end
        """, Long.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getSingleResult();
    }

    /** Đếm Imaging ServiceOrder theo RoomType trong khoảng */
    public Long countImagingOrdersBetween(Instant start, Instant end) {
        return em.createQuery("""
            select count(so)
            from sba.group3.backendmvc.entity.examination.ServiceOrder so
            where so.createdDate >= :start and so.createdDate < :end
              and so.room.roomType in (
                sba.group3.backendmvc.entity.organization.RoomType.XRAY,
                sba.group3.backendmvc.entity.organization.RoomType.ULTRASOUND,
                sba.group3.backendmvc.entity.organization.RoomType.ENDOSCOPY,
                sba.group3.backendmvc.entity.organization.RoomType.ECG,
                sba.group3.backendmvc.entity.organization.RoomType.CT_SCAN,
                sba.group3.backendmvc.entity.organization.RoomType.DEXA,
                sba.group3.backendmvc.entity.organization.RoomType.EEG
              )
        """, Long.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getSingleResult();
    }

    /** Đếm total queue + waiting + inService + invoices + completed exams trong ngày */
    public Long countQueuesBetween(Instant start, Instant end) {
        return em.createQuery("""
            select count(q)
            from sba.group3.backendmvc.entity.appointment.QueueTicket q
            where q.createdDate >= :start and q.createdDate < :end
        """, Long.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getSingleResult();
    }

    public Long countQueuesByStatusBetween(Instant start, Instant end, Enum<?> status) {
        return em.createQuery("""
            select count(q)
            from sba.group3.backendmvc.entity.appointment.QueueTicket q
            where q.createdDate >= :start and q.createdDate < :end
              and q.status = :st
        """, Long.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .setParameter("st", status)
                .getSingleResult();
    }

    public Long countExamsByStatusBetween(Instant start, Instant end, Enum<?> status) {
        return em.createQuery("""
            select count(e)
            from sba.group3.backendmvc.entity.examination.Examination e
            where e.createdDate >= :start and e.createdDate < :end
              and e.status = :st
        """, Long.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .setParameter("st", status)
                .getSingleResult();
    }

    public Long countInvoicesByCreatedBetween(Instant start, Instant end) {
        return em.createQuery("""
            select count(i)
            from sba.group3.backendmvc.entity.billing.Invoice i
            where i.createdDate >= :start and i.createdDate < :end
        """, Long.class)
                .setParameter("start", start)
                .setParameter("end", end)
                .getSingleResult();
    }

}
