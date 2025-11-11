package sba.group3.backendmvc.controller.infrastructure;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.errors.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.dto.response.common.FileAttachmentResponse;
import sba.group3.backendmvc.entity.common.FileAttachment;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.repository.common.FileAttachmentRepository;
import sba.group3.backendmvc.service.infrastructure.FileStorageService;
import sba.group3.backendmvc.service.infrastructure.FileUploadService;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@FieldDefaults(level = lombok.AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "File Management", description = "APIs for managing file uploads and downloads")
public class FileController {

    FileAttachmentRepository fileAttachmentRepository;
    MinioClient minioClient;
    FileUploadService fileUploadService;

    @NonFinal
    @Value("${minio.bucket}")
    String bucket;

    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewFile(@PathVariable UUID id) throws ServerException, InsufficientDataException, ErrorResponseException, IOException, NoSuchAlgorithmException, InvalidKeyException, InvalidResponseException, XmlParserException, InternalException {
        FileAttachment file = fileAttachmentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.UNAUTHORIZED));

        InputStream stream = minioClient.getObject(
                GetObjectArgs.builder()
                        .bucket(bucket)
                        .object(file.getFileName())
                        .build()
        );

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(file.getContentType()))
                .body(new InputStreamResource(stream));
    }


    @GetMapping
    public ResponseEntity<CustomApiResponse<List<FileAttachmentResponse>>> getFiles(
            @RequestParam String entityType,
            @RequestParam String entityId
    ) {
        var files = fileUploadService.getAttachments(entityType, entityId);
        return ResponseEntity.ok(
                CustomApiResponse.<List<FileAttachmentResponse>>builder()
                        .data(files)
                        .build()
        );
    }

}
