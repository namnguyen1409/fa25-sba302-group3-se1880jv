package sba.group3.backendmvc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@ConfigurationProperties(prefix = "upload")
@Data
public class UploadProperties {
    private Map<String, UploadRule> rules = new HashMap<>();

    @Data
    public static class UploadRule {
        private String maxSize;
        private List<String> allowedTypes;
        private String purpose;
        private List<String> rolesAllowed;
        private Boolean checkOwner = false;
    }

    public long parseSize(String size) {
        if (size == null) return 10 * 1024 * 1024L;
        size = size.toUpperCase();
        if (size.endsWith("MB")) return Long.parseLong(size.replace("MB", "")) * 1024 * 1024;
        if (size.endsWith("KB")) return Long.parseLong(size.replace("KB", "")) * 1024;
        return Long.parseLong(size);
    }
}
