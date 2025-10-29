package sba.group3.backendmvc.entity.examination;

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
@Table(name = "service_catalog", schema = "examination")
public class ServiceCatalog extends BaseEntity {

    @Column(name = "code", nullable = false, unique = true, length = 20)
    String code;

    @Column(name = "name", nullable = false, length = 150)
    String name;

    @Column(name = "category", length = 100)
    String category; // VD: "Xét nghiệm", "Chẩn đoán hình ảnh", "Thủ thuật"

    @Column(name = "price", nullable = false)
    BigDecimal price;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

}