package sba.group3.backendmvc.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@FieldDefaults(level = AccessLevel.PROTECTED)
@FieldNameConstants
@SuperBuilder
@Indexed
public abstract class BaseEntity {
    @Id
    @GenericField
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    UUID id;

    @CreatedBy
    @Column(name = "created_by")
    String createdBy;

    @CreatedDate
    @Column(name = "created_date")
    Instant createdDate;

    @LastModifiedBy
    @Column(name = "updated_by")
    String lastModifiedBy;

    @LastModifiedDate
    @Column(name = "updated_date")
    Instant lastModifiedDate;

    @Version
    @Column(name = "version")
    Integer version;

    @Builder.Default
    @Column(nullable = false)
    Boolean deleted = false;

}