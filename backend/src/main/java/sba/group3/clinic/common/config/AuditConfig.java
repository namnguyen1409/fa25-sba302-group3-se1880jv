package sba.group3.clinic.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.context.SecurityContextHolder;
import sba.group3.clinic.auth.security.CustomUserDetails;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return () -> {
            try {
                var userDetail = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                return Optional.of(userDetail.getUser().getId().toString());
            } catch (Exception ex) {
                return Optional.of("SYSTEM");
            }

        };
    }

}
