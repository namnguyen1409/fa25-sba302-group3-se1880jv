package sba.group3.backendmvc.service.infrastructure;

import java.io.InputStream;

public interface EmailSender {

    void sendOtp(String to, String otp);

    void send(String to, String subject, String htmlContent);

    void send(
            String to,
            String subject,
            String htmlContent,
            String attachmentFilename,
            InputStream inputStream
    );

    void sendSecurityAlert(String to, String content);
}
