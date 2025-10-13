package sba.group3.clinic.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.FieldNameConstants;
import org.springframework.http.MediaType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "file_attachment", schema = "common")
@FieldDefaults(level = AccessLevel.PRIVATE)
@FieldNameConstants
public class FileAttachment extends BaseEntity {
    /**
     * Tên file gốc khi upload lên
     */
    @Column(name = "file_name", nullable = false)
    String fileName;

    /**
     * Content type (MIME type) của file
     */
    @Column(name = "content_type", nullable = false)
    String contentType;

    /**
     * Kích thước file (tính bằng byte)
     */
    @Column(name = "size", nullable = false)
    private Long size;

    /**
     * URL để truy cập file (có thể là URL trên server hoặc URL trên dịch vụ lưu trữ đám mây)
     */
    @Column(name = "url", nullable = false)
    private String url;

    /**
     * Loại thực thể mà file này liên kết đến (ví dụ: "User", "Product", "Order", v.v.)
     */
    @Column(name = "entity_type", nullable = false)
    private String entityType;

    /**
     * ID của thực thể mà file này liên kết đến
     */
    @Column(name = "entity_id", nullable = false)
    private String entityId;

}