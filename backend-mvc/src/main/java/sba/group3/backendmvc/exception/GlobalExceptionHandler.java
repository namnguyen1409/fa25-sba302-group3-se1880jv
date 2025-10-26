package sba.group3.backendmvc.exception;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.resource.NoResourceFoundException;
import sba.group3.backendmvc.dto.response.CustomApiResponse;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final HttpServletRequest httpServletRequest;

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ResponseEntity<CustomApiResponse<Void>> handlingRuntimeException(
            Exception exception
    ) {
        exception.printStackTrace();
        CustomApiResponse<Void> customApiResponse = new CustomApiResponse<>();

        customApiResponse.setCode(ErrorCode.UNCATEGORIZED.getCode());
        customApiResponse.setMessage(ErrorCode.UNCATEGORIZED.getMessage());
        log.error("Unhandled exception {}", (Object) exception.getStackTrace());
        return ResponseEntity.badRequest().body(customApiResponse);
    }

    @ExceptionHandler(value = AccessDeniedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    ResponseEntity<CustomApiResponse<Object>> handlingAccessDeniedException() {
        ErrorCode errorCode = ErrorCode.UNAUTHORIZED;

        return ResponseEntity.status(errorCode.getCode())
                .body(CustomApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }

    @ExceptionHandler(value = AppException.class)
    ResponseEntity<CustomApiResponse<Object>> handlingAppException(AppException exception) {
        log.warn(exception.getMessage());
        ErrorCode errorCode = exception.getErrorCode();
        return ResponseEntity.status(errorCode.getHttpStatus())
                .body(CustomApiResponse.builder()
                        .code(errorCode.getCode())
                        .message(errorCode.getMessage())
                        .build());
    }


    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<CustomApiResponse<Object>> handleBindException(BindException e) {
        Map<String, String> errors = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            String field = error.getField();
            String message = error.getDefaultMessage();
            errors.put(field, message);
        });

        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Validation failed")
                .data(errors)
                .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(NoResourceFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<CustomApiResponse<Object>> handleNoResourceFoundException(NoResourceFoundException e) {
        CustomApiResponse<Object> response = CustomApiResponse.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("Cannot find the requested resource" + httpServletRequest.getServletPath())
                .build();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }


}