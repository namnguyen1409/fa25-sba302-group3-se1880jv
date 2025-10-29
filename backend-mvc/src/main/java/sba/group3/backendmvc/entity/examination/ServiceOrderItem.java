package sba.group3.backendmvc.entity.examination;

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
@Table(name = "service_order_item", schema = "examination")
public class ServiceOrderItem extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_order_id")
    ServiceOrder serviceOrder;

    @ManyToOne(optional = false)
    @JoinColumn(name = "service_id")
    ServiceCatalog service;  // tham chiếu đến dịch vụ chuẩn hóa

    @Column(name = "price")
    BigDecimal price; // có thể copy từ catalog để lưu snapshot giá tại thời điểm khám

    @Column(name = "note", columnDefinition = "TEXT")
    String note;
}