package sba.group3.backendmvc.entity.common;

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
@Entity
@Table(name = "file_attachment", schema = "common")
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class FileAttachment extends BaseEntity {

    @Column(name = "file_name", nullable = false)
    String fileName;

    @Column(name = "content_type", nullable = false)
    String contentType;

    @Column(name = "size", nullable = false)
    Long size;

    @Column(name = "url", nullable = false)
    String url;

    @Column(name = "entity_type")
    String entityType;

    @Column(name = "entity_id")
    String entityId;

    @Column(name = "upload_purpose")
    String uploadPurpose;

}