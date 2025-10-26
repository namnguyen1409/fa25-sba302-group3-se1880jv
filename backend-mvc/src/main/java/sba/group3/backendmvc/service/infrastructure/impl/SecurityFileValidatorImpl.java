package sba.group3.backendmvc.service.infrastructure.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.tika.Tika;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sba.group3.backendmvc.config.UploadProperties;
import sba.group3.backendmvc.exception.AppException;
import sba.group3.backendmvc.exception.ErrorCode;
import sba.group3.backendmvc.service.auth.SecurityService;
import sba.group3.backendmvc.service.infrastructure.SecurityFileValidator;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class SecurityFileValidatorImpl implements SecurityFileValidator {

    Tika tika = new Tika();
    SecurityService securityService;
    UploadProperties uploadProperties;

    @Override
    public void validate(MultipartFile file, UploadProperties.UploadRule rule, String entityType, String entityId) throws Exception {
        if (!securityService.hasAnyRole(rule.getRolesAllowed().toArray(new String[0]))) {
            throw new AppException(ErrorCode.UNAUTHORIZED, "User does not have permission to upload files for entity type: " + entityType);
        }
        String detectedType = tika.detect(file.getInputStream());
        if (!rule.getAllowedTypes().contains(detectedType)) {
            throw new AppException(ErrorCode.INVALID_FILE_UPLOAD);
        }
        var maxSize = uploadProperties.parseSize(rule.getMaxSize());
        if (file.getSize() > maxSize) {
            throw new AppException(ErrorCode.FILE_SIZE_EXCEEDED);
        }

    }


}
