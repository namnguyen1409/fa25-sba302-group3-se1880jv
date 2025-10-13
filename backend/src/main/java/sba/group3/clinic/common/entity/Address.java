package sba.group3.clinic.common.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "address", schema = "common")
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class Address extends BaseEntity {
    @Column(name = "street")
    String street;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "ward_id")
    AdministrativeUnit ward;

    @Column(name = "ward_name")
    String wardName;

    @Column(name = "district_name")
    String districtName;

    @Column(name = "city_name")
    String city;

}