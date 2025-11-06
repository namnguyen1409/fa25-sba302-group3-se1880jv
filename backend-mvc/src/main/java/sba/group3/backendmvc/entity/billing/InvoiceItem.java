package sba.group3.backendmvc.entity.billing;

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
@Table(name = "invoice_item", schema = "billing")
public class InvoiceItem extends BaseEntity {
    @ManyToOne(optional = false)
    @JoinColumn(name = "invoice_id", nullable = false)
    Invoice invoice;

    @Column(name = "description", nullable = false, length = 255)
    String description;

    @Builder.Default
    @Column(name = "quantity", nullable = false)
    Integer quantity = 1;

    @Column(name = "unit_price", nullable = false)
    BigDecimal unitPrice;

    @Column(name = "total_price", nullable = false)
    BigDecimal totalPrice;
}