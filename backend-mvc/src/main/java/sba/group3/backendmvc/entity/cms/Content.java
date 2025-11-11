package sba.group3.backendmvc.entity.cms;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import lombok.experimental.SuperBuilder;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.GenericField;
import sba.group3.backendmvc.entity.BaseEntity;
import sba.group3.backendmvc.entity.staff.Staff;

import java.time.Instant;
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
@Table(name = "content", schema = "content_management")
public class Content extends BaseEntity {
    @FullTextField(analyzer = "standard")
    @Column(nullable = false, length = 200)
    String title;

    @GenericField
    @Column(nullable = false, unique = true, length = 220)
    String slug;

    @FullTextField(analyzer = "standard")
    @Column(columnDefinition = "TEXT", nullable = false)
    String body;

    @FullTextField(analyzer = "standard")
    @Column(length = 500)
    String excerpt;

    @Column(length = 500)
    String coverImageUrl;

    @Enumerated(EnumType.STRING)
    @GenericField
    @Column(length = 20, nullable = false)
    ContentStatus status;

    @GenericField
    Instant publishedAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "author_id", nullable = false)
    Staff author;


    @ElementCollection
    @CollectionTable(name = "content_tag",
            schema = "content_management",
            joinColumns = @JoinColumn(name = "content_id")
    )
    @Column(name = "tag", length = 50)
    @FullTextField(analyzer = "standard")
    @Builder.Default
    Set<String> tags = new HashSet<>();
}
