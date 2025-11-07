package sba.group3.backendmvc.repository.common;

import sba.group3.backendmvc.entity.common.FileAttachment;
import sba.group3.backendmvc.repository.BaseRepository;

import java.util.List;
import java.util.UUID;

public interface FileAttachmentRepository extends BaseRepository<FileAttachment, UUID> {
    List<FileAttachment> findAllByEntityTypeAndEntityId(String entityType, String entityId);
}