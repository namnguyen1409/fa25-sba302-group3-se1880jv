package sba.group3.backendmvc.service.infrastructure;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String upload(MultipartFile file, String objectName) throws Exception;
}
