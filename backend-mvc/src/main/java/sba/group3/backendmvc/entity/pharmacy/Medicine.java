package sba.group3.backendmvc.entity.pharmacy;

import jakarta.persistence.*;
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
@Table(name = "medicine", schema = "pharmacy")
public class Medicine extends BaseEntity {
    @Column(name = "code", nullable = false, unique = true, length = 30)
    String code;

    @Column(name = "name", nullable = false, length = 150)
    String name; // Tên thuốc thương mại (biệt dược)

    @Column(name = "active_ingredient", length = 150)
    String activeIngredient; // Thành phần hoạt chất

    @Column(name = "dosage_form", length = 100)
    String dosageForm; // Dạng bào chế: viên, gói, ống, siro,...

    @Column(name = "strength", length = 50)
    String strength; // Hàm lượng: 500mg, 5ml,...

    @Column(name = "price", nullable = false)
    BigDecimal price;

    @Column(name = "unit", length = 30)
    String unit;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;
}