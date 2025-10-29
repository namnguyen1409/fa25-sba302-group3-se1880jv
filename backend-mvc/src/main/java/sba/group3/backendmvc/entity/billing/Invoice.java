package sba.group3.backendmvc.entity.billing;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.examination.Examination;
import sba.group3.backendmvc.entity.patient.Patient;

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

    @Column(name = "issue_date", nullable = false)
    LocalDateTime issueDate = LocalDateTime.now();

    @Column(name = "total_amount", nullable = false)
    BigDecimal totalAmount;

    @Column(name = "paid", nullable = false)
    Boolean paid = false; // ✅ chỉ để xác nhận bằng tay

    @Column(name = "note", columnDefinition = "TEXT")
    String note;

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<InvoiceItem> items = new HashSet<>();

}