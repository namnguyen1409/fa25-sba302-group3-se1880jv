package sba.group3.backendmvc.entity.laboratory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "lab_test", schema = "laboratory")
public class LabTest extends BaseEntity {

    @Column(name = "code", nullable = false, unique = true, length = 20)
    String code;

    @Column(name = "name", nullable = false, length = 150)
    String name;

    @Column(name = "category", length = 100)
    String category; // VD: "Huyết học", "Sinh hóa", "Vi sinh"

    @Column(name = "price", nullable = false)
    BigDecimal price;

    @Column(name = "unit", length = 50)
    String unit;

    @Column(name = "reference_range", length = 100)
    String referenceRange;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;
}