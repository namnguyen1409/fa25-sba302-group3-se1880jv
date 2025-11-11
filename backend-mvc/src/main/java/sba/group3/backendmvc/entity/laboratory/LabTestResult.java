package sba.group3.backendmvc.entity.laboratory;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.staff.Staff;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "lab_test_result", schema = "laboratory")
public class LabTestResult extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "lab_order_id")
    LabOrder labOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "lab_test_id")
    LabTest labTest;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 30)
    LabStatus status = LabStatus.PENDING;

    @Column(name = "result_value", length = 100)
    String resultValue;

    @Column(name = "unit", length = 50)
    String unit;

    @Column(name = "reference_range", length = 500)
    String referenceRange;

    @Column(name = "remark", columnDefinition = "TEXT")
    String remark;

    @ManyToOne
    @JoinColumn(name = "verified_by")
    Staff verifiedBy;

    @Column(name = "verified_at")
    LocalDateTime verifiedAt;

}