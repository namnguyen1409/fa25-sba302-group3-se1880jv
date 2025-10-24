package sba.group3.backendmvc.service.infrastructure.impl;

import jakarta.annotation.Resource;
import jakarta.mail.internet.MimeMessage;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.service.infrastructure.EmailSender;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailSenderImpl implements EmailSender {

    JavaMailSender javaMailSender;

    @Resource
    EmailSender self;

    @NonFinal
    @Value("${spring.mail.username}")
    String from;

    @Async("taskExecutor")
    @Override
    public void sendOtp(String to, String otp) {
        String subject = "Your OTP Code";
        String htmlContent = "<p>Your OTP code is: <strong>" + otp + "</strong></p>"
                + "<p>This code is valid for 10 minutes.</p>";
        self.send(to, subject, htmlContent);
    }

    @Async("taskExecutor")
    @Override
    public void send(String to, String subject, String htmlContent) {
        self.send(to, subject, htmlContent, null, null);
    }

    @Async("taskExecutor")
    @Override
    public void send(
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
