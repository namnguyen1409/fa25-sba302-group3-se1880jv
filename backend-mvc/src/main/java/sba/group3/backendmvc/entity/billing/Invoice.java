package sba.group3.backendmvc.entity.billing;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.organization.Room;
import sba.group3.backendmvc.entity.patient.Patient;
import sba.group3.backendmvc.entity.staff.Staff;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "invoice", schema = "billing")
public class Invoice extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "patient_id", nullable = false)
    Patient patient;

    @OneToOne
    @JoinColumn(name = "examination_id")
    Examination examination;

    @Column(name = "invoice_number", nullable = false, unique = true, length = 30)
    String invoiceNumber;

    @Builder.Default
    @Column(name = "issue_date", nullable = false)
    LocalDateTime issueDate = LocalDateTime.now();

    @Column(name = "total_amount", nullable = false)
    BigDecimal totalAmount;

    @Builder.Default
    @Column(name = "paid", nullable = false)
    Boolean paid = false;

    @Column(name = "note", columnDefinition = "TEXT")
    String note;

    @Builder.Default
    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<InvoiceItem> items = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "room_id")
    private Room room;

    @ManyToOne
    @JoinColumn(name = "assigned_staff_id")
    Staff assignedStaff;

}