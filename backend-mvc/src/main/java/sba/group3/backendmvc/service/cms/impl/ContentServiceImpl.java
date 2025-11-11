package sba.group3.backendmvc.service.cms.impl;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.dto.filter.SearchFilter;
import sba.group3.backendmvc.dto.request.cms.ContentRequest;
import sba.group3.backendmvc.dto.response.cms.ContentResponse;
import sba.group3.backendmvc.entity.cms.ContentStatus;
import sba.group3.backendmvc.mapper.cms.ContentMapper;
import sba.group3.backendmvc.repository.cms.ContentRepository;
import sba.group3.backendmvc.repository.staff.StaffRepository;
import sba.group3.backendmvc.service.cms.ContentService;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
public class ContentServiceImpl implements ContentService {
    private final ContentRepository contentRepository;
    private final ContentMapper contentMapper;
    private final StaffRepository staffRepository;

    @Override
    public Page<ContentResponse> filter(SearchFilter filter) {
        return contentRepository.search(filter).map(contentMapper::toDto1);
    }

    @Override
    public ContentResponse getById(UUID id) {
        return contentRepository.findById(id)
                .map(contentMapper::toDto1)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

    @Override
    public ContentResponse getBySlug(String slug) {
        return contentRepository.findBySlugAndDeletedFalse(slug)
                .map(contentMapper::toDto1)
                .orElseThrow(() -> new RuntimeException("Content not found"));
    }

    @Override
    public ContentResponse create(ContentRequest req, UUID authorUserId) {
        var content = contentMapper.toEntity(req);
        content.setAuthor(staffRepository.getReferenceById(authorUserId));
        var savedContent = contentRepository.save(content);
        if (content.getStatus() == ContentStatus.PUBLISHED) {
            content.setPublishedAt(Instant.now());
        }
        return contentMapper.toDto1(savedContent);
    }

    @Override
    public ContentResponse update(UUID id, ContentRequest req) {
        var content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
        if (content.getStatus() != req.status()) {
            if (req.status() == ContentStatus.PUBLISHED) {
                content.setPublishedAt(Instant.now());
            } else if (req.status() == ContentStatus.DRAFT) {
                content.setPublishedAt(null);
            }
        }
        contentMapper.partialUpdate(req, content);
        var updatedContent = contentRepository.save(content);
        return contentMapper.toDto1(updatedContent);
    }

    @Override
    public void delete(UUID id) {
        var content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
        content.setDeleted(true);
        contentRepository.save(content);
    }

    @Override
    public ContentResponse publish(UUID id) {
        var content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
        content.setStatus(ContentStatus.PUBLISHED);
        content.setPublishedAt(Instant.now());
        var updatedContent = contentRepository.save(content);
        return contentMapper.toDto1(updatedContent);
    }

    @Override
    public ContentResponse archive(UUID id) {
        var content = contentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Content not found"));
        content.setStatus(ContentStatus.ARCHIVED);
        content.setPublishedAt(null);
        var updatedContent = contentRepository.save(content);
        return contentMapper.toDto1(updatedContent);
    }
}
