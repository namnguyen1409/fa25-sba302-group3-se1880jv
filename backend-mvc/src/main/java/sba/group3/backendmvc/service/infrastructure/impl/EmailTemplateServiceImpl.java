package sba.group3.backendmvc.service.infrastructure.impl;

import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.service.infrastructure.EmailTemplateService;

import java.io.StringWriter;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailTemplateServiceImpl implements EmailTemplateService {

    private final Configuration freemarkerConfig;

    @Override
    public String render(String templateName, Map<String, String> model) {
        try {
            Template template = freemarkerConfig.getTemplate(templateName);
            StringWriter writer = new StringWriter();
            template.process(model, writer);
            return writer.toString();
        } catch (Exception e) {
            throw new RuntimeException("Failed to render email template: " + templateName, e);
        }
    }

}
