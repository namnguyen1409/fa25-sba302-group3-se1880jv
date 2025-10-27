package sba.group3.backendmvc.service.infrastructure.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sba.group3.backendmvc.config.UploadProperties;
import sba.group3.backendmvc.entity.common.FileAttachment;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.repository.common.FileAttachmentRepository;
import sba.group3.backendmvc.service.infrastructure.FileStorageService;
import sba.group3.backendmvc.service.infrastructure.FileUploadService;
import sba.group3.backendmvc.service.infrastructure.SecurityFileValidator;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class FileUploadServiceImpl implements FileUploadService {

    FileAttachmentRepository fileAttachmentRepository;
    FileStorageService fileStorageService;
    UploadProperties uploadProps;
    SecurityFileValidator securityValidator;

    @Override
    public String upload(MultipartFile file, String entityType, String entityId) throws Exception {
        var rule = uploadProps.getRules().get(entityType);
        if (rule == null) throw new AppException(ErrorCode.UPLOAD_NOT_ALLOWED, "Upload not allowed for entity type: " + entityType);

        securityValidator.validate(file, rule, entityType, entityId);

        String objectName = entityType + "/" + entityId + "/" + UUID.randomUUID() + "_" + file.getOriginalFilename();
        String fileUrl = fileStorageService.upload(file, objectName);

        FileAttachment attachment = FileAttachment.builder()
                .fileName(objectName)
                .contentType(file.getContentType())
                .size(file.getSize())
                .url(fileUrl)
                .entityType(entityType)
                .entityId(entityId)
                .uploadPurpose(rule.getPurpose())
                .build();

        var id = fileAttachmentRepository.save(attachment).getId();
        return "/api/files/view/" + id;
    }

}
