package sba.group3.clinic.user.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import sba.group3.clinic.common.entity.BaseEntity;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(
        name = "role",
        schema = "user_management",
        indexes = {
        @Index(name = "idx_role_name_unq", columnList = "name", unique = true)
})
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class Role extends BaseEntity {

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(
            name = "role_permissions",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permissions_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "permissions_id"})
    )
    private Set<Permission> permissions = new LinkedHashSet<>();

}