package sba.group3.backendmvc.entity.common;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "address", schema = "common")
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class Address extends BaseEntity {
    @Column(name = "street")
    String street;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    AdministrativeUnit ward;

    @Column(name = "ward_name")
    String wardName;

    @Column(name = "district_name")
    String districtName;

    @Column(name = "city_name")
    String city;

}