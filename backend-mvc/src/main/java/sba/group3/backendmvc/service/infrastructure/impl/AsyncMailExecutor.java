package sba.group3.backendmvc.service.infrastructure.impl;

import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@Slf4j
@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AsyncMailExecutor {

    private final JavaMailSender javaMailSender;

    @Async("taskExecutor")
    public void send(
            String from,
            String to,
            String subject,
            String htmlContent,
            String attachmentFilename,
            InputStream inputStream
    ) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(htmlContent, true);
            if (inputStream != null) {
                helper.addAttachment(attachmentFilename, () -> inputStream);
            }
            javaMailSender.send(mimeMessage);
            log.info("Email send to {} with subject {}", to, subject);
        } catch (Exception e) {
            log.error("Failed to send email to {}: {}", to, e.getMessage());
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (Exception ignore) {
                    // Ignore
                }
            }
        }
    }

}
