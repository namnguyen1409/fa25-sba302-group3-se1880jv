package sba.group3.backendmvc.controller.infrastructure;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sba.group3.backendmvc.dto.response.CustomApiResponse;
import sba.group3.backendmvc.service.infrastructure.FileUploadService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Tag(name = "File Upload", description = "Upload file for service order, examination, lab result, etc.")
public class FileUploadController {

    FileUploadService fileUploadService;

    /**
     * ✅ Upload 1 file kết quả
     */
    @PostMapping(
            value = "/upload",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<CustomApiResponse<String>> uploadSingle(
            @RequestParam("file") MultipartFile file,
            @RequestParam("entityType") String entityType,
            @RequestParam("entityId") String entityId
    ) {
        try {
            String fileUrl = fileUploadService.upload(file, entityType, entityId);
            return ResponseEntity.ok(
                    CustomApiResponse.<String>builder()
                            .data(fileUrl)
                            .message("Upload successful")
                            .build()
            );
        } catch (Exception e) {
            log.error("File upload failed", e);
            return ResponseEntity.badRequest().body(
                    CustomApiResponse.<String>builder()
                            .message("Upload failed: " + e.getMessage())
                            .build()
            );
        }
    }

    /**
     * ✅ Upload nhiều file cùng lúc
     */
    @PostMapping(
            value = "/upload-multiple",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<CustomApiResponse<List<String>>> uploadMultiple(
            @RequestParam("files") List<MultipartFile> files,
            @RequestParam("entityType") String entityType,
            @RequestParam("entityId") String entityId
    ) {
        List<String> urls = new ArrayList<>();

        try {
            for (MultipartFile file : files) {
                String url = fileUploadService.upload(file, entityType, entityId);
                urls.add(url);
            }

            return ResponseEntity.ok(
                    CustomApiResponse.<List<String>>builder()
                            .data(urls)
                            .message("Upload successful")
                            .build()
            );

        } catch (Exception e) {
            log.error("Multiple file upload failed", e);
            return ResponseEntity.badRequest().body(
                    CustomApiResponse.<List<String>>builder()
                            .message("Upload failed: " + e.getMessage())
                            .build()
            );
        }
    }

}
