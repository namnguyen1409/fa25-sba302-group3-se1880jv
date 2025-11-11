package sba.group3.backendmvc.dto.request.cms;

import sba.group3.backendmvc.entity.cms.ContentStatus;

import java.io.Serializable;
import java.util.Set;

/**
 * DTO for {@link sba.group3.backendmvc.entity.cms.Content}
 */
public record ContentRequest(String title, String slug, String body, String excerpt, String coverImageUrl,
                             ContentStatus status, Set<String> tags) implements Serializable {
}