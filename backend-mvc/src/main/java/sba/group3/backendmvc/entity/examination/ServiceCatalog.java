package sba.group3.backendmvc.entity.examination;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.organization.RoomType;

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

    @Column(name = "name", nullable = false, length = 500)
    String name;

    @Column(name = "category", length = 500)
    String category; // VD: "Xét nghiệm", "Chẩn đoán hình ảnh", "Thủ thuật"

    @Column(name = "price", nullable = false)
    BigDecimal price;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", length = 50)
    RoomType roomType;

}