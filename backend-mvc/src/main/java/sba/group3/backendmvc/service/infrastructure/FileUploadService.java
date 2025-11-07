package sba.group3.backendmvc.service.infrastructure;

import org.springframework.web.multipart.MultipartFile;
import sba.group3.backendmvc.dto.response.common.FileAttachmentResponse;

import java.util.List;

public interface FileUploadService {
    String upload(MultipartFile file, String entityType, String entityId) throws Exception;

    List<FileAttachmentResponse> getAttachments(String entityType, String entityId);
}
