package sba.group3.backendmvc.entity.organization;

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
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
@Table(name = "room", schema = "organization")
public class Room extends BaseEntity {

    @Column(name = "name", nullable = false, length = 100, unique = true)
    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "room_type", length = 50)
    RoomType roomType;

    @Column(name = "floor_number")
    Integer floorNumber;

    @Column(name = "capacity")
    Integer capacity;

    @Column(name = "description", columnDefinition = "TEXT")
    String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "department_id", nullable = false)
    Department department;
}