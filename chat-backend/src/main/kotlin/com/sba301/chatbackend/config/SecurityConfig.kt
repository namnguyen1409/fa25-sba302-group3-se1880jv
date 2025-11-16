package com.sba301.chatbackend.config

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.CorsConfigurationSource
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource
import reactor.core.publisher.Mono

@Configuration
@EnableWebFluxSecurity
class SecurityConfig(
    private val securityProperties: SecurityProperties,
    private val jwtConfig: JwtDecoderConfig
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        return UrlBasedCorsConfigurationSource().apply {
            registerCorsConfiguration("/**", CorsConfiguration().apply {
                allowedHeaders = securityProperties.allowedHeaders
                allowedMethods = securityProperties.allowedMethods.ifEmpty {
                    listOf("GET", "POST", "PUT", "DELETE", "OPTIONS")
                }
                allowedOriginPatterns = securityProperties.allowedOrigins.ifEmpty {
                    listOf("*")
                }
                allowCredentials = securityProperties.allowedCredentials
                maxAge = 3600L
            })
        }
    }


    @Bean
    fun securityWebFilterChain(http: ServerHttpSecurity, decoder: ReactiveJwtDecoder): SecurityWebFilterChain {
        return http
            .csrf { it.disable() }
            .cors { it.configurationSource(corsConfigurationSource()) }
            .oauth2ResourceServer { oauth ->
                oauth.jwt { jwt ->
                    jwt.jwtDecoder(jwtConfig.jwtDecoder())
                    jwt.jwtAuthenticationConverter { jwtToken ->
                        val sub = jwtToken.subject ?: jwtToken.getClaimAsString("username")
                        val authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))
//                        log.info("🪪 Decoded JWT: sub=$sub, aud=${jwtToken.audience}, iss=${jwtToken.issuer}")
                        Mono.just(JwtAuthenticationToken(jwtToken, authorities, sub))
                    }
                }
                oauth.authenticationEntryPoint { exchange, ex ->
                    val authHeader = exchange.request.headers.getFirst("Authorization")
//                    log.error("❌ JWT verification failed: ${ex.message}")
//                    log.warn("🔎 Authorization header = $authHeader")
                    exchange.response.statusCode = HttpStatus.UNAUTHORIZED
                    Mono.empty()
                }
            }
            .authorizeExchange { auth ->
                auth
                    .pathMatchers(
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/uploads/**",
                        "/ws/**",
                        "/public/**",
                    ).permitAll()
                    .pathMatchers("/api/sso/login").authenticated()
                    .pathMatchers("/api/auth/me").authenticated()
                    .pathMatchers("/api/**").authenticated()
                    .anyExchange().permitAll()
            }
            .build()
    }

}
