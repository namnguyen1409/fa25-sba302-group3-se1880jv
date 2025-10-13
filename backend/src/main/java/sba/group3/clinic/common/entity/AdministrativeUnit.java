package sba.group3.clinic.common.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import sba.group3.clinic.common.enums.AdministrativeUnitLevel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "administrative_unit", schema = "common")
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class AdministrativeUnit extends BaseEntity {
    /**
     * Mã hành chính (ví dụ: mã phường/xã, mã quận/huyện, mã tỉnh/thành phố)
     */
    @Column(name = "code", nullable = false, unique = true, length = 10)
    String code;

    /**
     * Tên hành chính (ví dụ: tên phường/xã, tên quận/huyện, tên tỉnh/thành phố)
     */
    @Column(name = "name", nullable = false, length = 100)
    String name;

    /**
     * Cấp hành chính (ví dụ: PHUONG_XA, QUAN_HUYEN, TINH_THANH)
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "level", nullable = false, length = 20)
    AdministrativeUnitLevel level;

    @ManyToOne(fetch =  FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    AdministrativeUnit parent;

}