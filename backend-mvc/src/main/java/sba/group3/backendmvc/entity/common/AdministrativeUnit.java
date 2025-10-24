package sba.group3.backendmvc.entity.common;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.enums.AdministrativeUnitLevel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "administrative_unit", schema = "common")
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class AdministrativeUnit extends BaseEntity {

    @Column(name = "code", nullable = false, unique = true, length = 10)
    String code;

    @Column(name = "name", nullable = false, length = 100)
    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false, length = 20)
    AdministrativeUnitLevel level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    AdministrativeUnit parent;

}