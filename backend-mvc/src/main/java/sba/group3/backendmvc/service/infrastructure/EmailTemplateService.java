package sba.group3.backendmvc.service.infrastructure;

import java.util.Map;

public interface EmailTemplateService {
    String render(String templateName, Map<String, String> model);
}
