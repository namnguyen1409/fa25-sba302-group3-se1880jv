package sba.group3.backendmvc.service.infrastructure;

import org.springframework.web.multipart.MultipartFile;

public interface FileUploadService {
    String upload(MultipartFile file, String entityType, String entityId) throws Exception;
}
