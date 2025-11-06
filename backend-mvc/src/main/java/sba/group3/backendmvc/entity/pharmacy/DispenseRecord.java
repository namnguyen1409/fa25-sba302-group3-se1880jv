package sba.group3.backendmvc.entity.pharmacy;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.examination.Prescription;
import sba.group3.backendmvc.entity.staff.Staff;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "dispense_record", schema = "pharmacy")
public class DispenseRecord extends BaseEntity {

    @OneToOne(optional = false)
    @JoinColumn(name = "prescription_id", unique = true)
    Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "dispensed_by")
    Staff dispensedBy;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    DispenseStatus status = DispenseStatus.PENDING;

    @Column(name = "dispensed_at")
    LocalDateTime dispensedAt;

    @Column(name = "total_cost")
    BigDecimal totalCost;

    @Column(name = "note", columnDefinition = "TEXT")
    String note;
}