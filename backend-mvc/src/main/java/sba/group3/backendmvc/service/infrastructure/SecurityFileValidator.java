package sba.group3.backendmvc.service.infrastructure;

import org.springframework.web.multipart.MultipartFile;
import sba.group3.backendmvc.config.UploadProperties;

public interface SecurityFileValidator {

    void validate(MultipartFile file, UploadProperties.UploadRule rule, String entityType, String entityId) throws Exception;
}
