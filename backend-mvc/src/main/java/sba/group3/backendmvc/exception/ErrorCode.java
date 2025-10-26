package sba.group3.backendmvc.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public enum ErrorCode {
    AUTH_INVALID_CREDENTIALS(1001, "Invalid username or password", HttpStatus.UNAUTHORIZED),
    ACCOUNT_LOCKED(1002, "Account is locked", HttpStatus.LOCKED),
    UNCATEGORIZED(1999, "Uncategorized error", HttpStatus.INTERNAL_SERVER_ERROR),
    UNAUTHORIZED(2001, "Unauthorized access", HttpStatus.UNAUTHORIZED),
    INVALID_TOKEN(2002, "Invalid authentication token", HttpStatus.UNAUTHORIZED),
    PASSKEY_AUTHENTICATION_FAILED(3001, "Passkey authentication failed", HttpStatus.UNAUTHORIZED),
    USER_NOT_FOUND(4001, "User not found", HttpStatus.NOT_FOUND),
    OAUTH_PROVIDER_NOT_SUPPORTED(4002, "OAuth provider not supported", HttpStatus.BAD_REQUEST),
    REFRESH_TOKEN_MISSING(5002, "Refresh token is missing", HttpStatus.BAD_REQUEST),
    UNSUPPORTED_OAUTH_PROVIDER(4003, "Unsupported OAuth provider", HttpStatus.BAD_REQUEST),
    DEVICE_SESSION_NOT_FOUND(4004, "Device session not found", HttpStatus.NOT_FOUND),
    REFRESH_TOKEN_INVALID(5003, "Refresh token is invalid", HttpStatus.UNAUTHORIZED),
    INVALID_UPLOAD_ENTITY_TYPE(6001, "Invalid upload entity type", HttpStatus.BAD_REQUEST),
    TOKEN_EXPIRED(5001, "Token has expired", HttpStatus.UNAUTHORIZED),
    INVALID_FILE_UPLOAD(6002, "Invalid file upload", HttpStatus.BAD_REQUEST),
    FILE_SIZE_EXCEEDED(6003, "File size exceeded the maximum limit", HttpStatus.BAD_REQUEST),
    UPLOAD_NOT_ALLOWED(6004, "Upload not allowed for this entity type", HttpStatus.FORBIDDEN)
    ;
    int code;
    String message;
    HttpStatus httpStatus;
}
