package sba.group3.backendmvc.config;

import dev.samstevens.totp.code.*;
import dev.samstevens.totp.secret.DefaultSecretGenerator;
import dev.samstevens.totp.secret.SecretGenerator;
import dev.samstevens.totp.time.NtpTimeProvider;
import dev.samstevens.totp.time.TimeProvider;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@FieldDefaults(level = AccessLevel.PUBLIC, makeFinal = true)
public class TOTPConfig {

    final int DIGITS = 6;
    final int SECRET_CHARACTER_LENGTH = 32;
    final int CODE_VALIDITY_IN_SECONDS = 30;
    final int TIME_PERIOD_DISCREPANCY = 1;
    HashingAlgorithm HASHING_ALGO = HashingAlgorithm.SHA1;

    @Bean
    TimeProvider timeProvider() throws Exception {
        return new NtpTimeProvider("pool.ntp.org");
    }

    @Bean
    public SecretGenerator secretGenerator() {
        return new DefaultSecretGenerator(SECRET_CHARACTER_LENGTH);
    }

    @Bean
    CodeGenerator codeGenerator() {
        return new DefaultCodeGenerator(HASHING_ALGO, DIGITS);
    }

    @Bean
    public CodeVerifier codeVerifier() throws Exception {
        DefaultCodeVerifier verifier = new DefaultCodeVerifier(codeGenerator(), timeProvider());
        verifier.setTimePeriod(CODE_VALIDITY_IN_SECONDS);
        verifier.setAllowedTimePeriodDiscrepancy(TIME_PERIOD_DISCREPANCY);
        return verifier;
    }

}
