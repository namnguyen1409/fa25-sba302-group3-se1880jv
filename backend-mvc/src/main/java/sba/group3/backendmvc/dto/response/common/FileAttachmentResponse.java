package sba.group3.backendmvc.dto.response.common;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.common.FileAttachment}
 */
public record FileAttachmentResponse(UUID id, String fileName, String contentType, Long size, String url,
                                     String entityType, String entityId, String uploadPurpose) implements Serializable {
}