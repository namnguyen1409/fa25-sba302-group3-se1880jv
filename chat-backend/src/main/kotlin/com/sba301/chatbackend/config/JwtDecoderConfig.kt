package com.sba301.chatbackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.oauth2.core.OAuth2TokenValidatorResult
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager

@Configuration
class JwtDecoderConfig {
    @Bean
    fun jwtDecoder(): ReactiveJwtDecoder {
        val decoder = NimbusReactiveJwtDecoder
            .withJwkSetUri("https://backend.sba301.io.vn/.well-known/jwks.json")
            .build()
        decoder.setJwtValidator { token ->
//            println("🧩 iss = ${token.issuer}, aud = ${token.audience}")
            OAuth2TokenValidatorResult.success()
        }
        return decoder
    }

    @Bean
    fun jwtAuthManager(decoder: ReactiveJwtDecoder): JwtReactiveAuthenticationManager =
        JwtReactiveAuthenticationManager(decoder)
}