package sba.group3.backendmvc.dto.response.cms;

import sba.group3.backendmvc.dto.response.staff.StaffResponse;
import sba.group3.backendmvc.entity.cms.ContentStatus;

import java.io.Serializable;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

/**
 * DTO for {@link sba.group3.backendmvc.entity.cms.Content}
 */
public record ContentResponse(UUID id, String createdBy, Instant createdDate, String lastModifiedBy,
                              Instant lastModifiedDate, String title, String slug, String body, String excerpt,
                              String coverImageUrl, ContentStatus status, Instant publishedAt,
                              Set<String> tags, StaffResponse author) implements Serializable {
}