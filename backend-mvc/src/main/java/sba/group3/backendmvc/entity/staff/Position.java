package sba.group3.backendmvc.entity.staff;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Entity
@Table(name = "position", schema = "medical_staff_management")
public class Position extends BaseEntity {
    @Column(name = "position_code", nullable = false, unique = true, length = 20)
    String positionCode;

    @Column(name = "title", nullable = false, unique = true, length = 100)
    String title;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;
}