package sba.group3.backendmvc.service.infrastructure;

import org.springframework.web.multipart.MultipartFile;
import sba.group3.backendmvc.entity.common.FileAttachment;

public interface FileUploadService {
    FileAttachment upload(MultipartFile file, String entityType, String entityId) throws Exception;
}
