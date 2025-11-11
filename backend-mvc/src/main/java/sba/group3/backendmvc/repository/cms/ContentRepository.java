package sba.group3.backendmvc.repository.cms;

import sba.group3.backendmvc.entity.cms.Content;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends BaseRepository<Content, UUID> {
    Optional<Content> findBySlugAndDeletedFalse(String slug);
}