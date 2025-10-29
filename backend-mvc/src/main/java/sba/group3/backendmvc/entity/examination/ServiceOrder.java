package sba.group3.backendmvc.entity.examination;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;

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
@Table(name = "service_order", schema = "examination")
public class ServiceOrder extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "examination_id")
    Examination examination;

    @Column(name = "order_code", nullable = false, unique = true, length = 20)
    String orderCode;

    @OneToMany(mappedBy = "serviceOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<ServiceOrderItem> items = new HashSet<>();
}