package sba.group3.backendmvc.passkey;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.yubico.webauthn.CredentialRepository;
import com.yubico.webauthn.RelyingParty;
import com.yubico.webauthn.data.RelyingPartyIdentity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;
import java.util.Set;

@Configuration
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PasskeyConfig {

    final WebAuthnConfig webAuthnConfig;
    final CredentialRepository credentialRepository;

    @Bean
    RelyingParty relyingParty() {
        var rpIdentity = RelyingPartyIdentity.builder()
                .id(webAuthnConfig.getHostname())
                .name(webAuthnConfig.getDisplay())
                .build();
        return RelyingParty.builder()
                .identity(rpIdentity)
                .credentialRepository(credentialRepository)
                .origins(Collections.singleton(webAuthnConfig.getOrigin()))
                .build();
    }


    @Configuration
    @ConfigurationProperties(prefix = "security.webauthn")
    @Getter
    @Setter
    public static class WebAuthnConfig {
        private String hostname;
        private String display;
        private String origin;
    }

}
