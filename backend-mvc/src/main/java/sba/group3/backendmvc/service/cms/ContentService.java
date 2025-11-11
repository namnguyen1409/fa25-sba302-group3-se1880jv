package sba.group3.backendmvc.service.cms;

import org.springframework.data.domain.Page;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.cms.ContentRequest;
import sba.group3.backendmvc.dto.response.cms.ContentResponse;

import java.util.UUID;

public interface ContentService {
    Page<ContentResponse> filter(SearchFilter filter);
    ContentResponse getById(UUID id);
    ContentResponse getBySlug(String slug);
    ContentResponse create(ContentRequest req, UUID authorUserId);
    ContentResponse update(UUID id, ContentRequest req);
    void delete(UUID id); // soft
    ContentResponse publish(UUID id);
    ContentResponse archive(UUID id);
}
