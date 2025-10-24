package sba.group3.backendmvc.config;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import sba.group3.backendmvc.dto.response.CustomApiResponse;

import java.time.Instant;

@Component
@ControllerAdvice
public class CustomApiResponseAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // Áp dụng cho cả CustomApiResponse và ResponseEntity<CustomApiResponse>
        Class<?> paramType = returnType.getParameterType();
        return CustomApiResponse.class.isAssignableFrom(paramType)
                || ResponseEntity.class.isAssignableFrom(paramType);
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {

        if (body instanceof ResponseEntity<?> entity) {
            Object innerBody = entity.getBody();
            if (innerBody instanceof CustomApiResponse<?> apiResponse) {
                setMeta(apiResponse, request);
            }
            return entity;
        }

        if (body instanceof CustomApiResponse<?> apiResponse) {
            setMeta(apiResponse, request);
            return apiResponse;
        }

        return body;
    }

    private void setMeta(CustomApiResponse<?> res, ServerHttpRequest request) {
        if (res.getTimestamp() == null) {
            res.setTimestamp(Instant.now());
        }
        if (res.getPath() == null && request != null) {
            res.setPath(request.getURI().getPath());
        }
    }
}
