package sba.group3.backendmvc.service.infrastructure.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import sba.group3.backendmvc.service.infrastructure.EmailSender;

import java.io.InputStream;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailSenderImpl implements EmailSender {

    AsyncMailExecutor asyncMailExecutor;

    @NonFinal
    @Value("${spring.mail.username}")
    String from;

    @Async("taskExecutor")
    @Override
    public void sendOtp(String to, String otp) {
        String subject = "Your OTP Code";
        String htmlContent = "<p>Your OTP code is: <strong>" + otp + "</strong></p>"
                + "<p>This code is valid for 10 minutes.</p>";
        asyncMailExecutor.send(from, to, subject, htmlContent, null, null);
    }

    @Async("taskExecutor")
    @Override
    public void send(String to, String subject, String htmlContent) {
        asyncMailExecutor.send(from, to, subject, htmlContent, null, null);
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
        asyncMailExecutor.send(from, to, subject, htmlContent, attachmentFilename, inputStream);
    }


}
