package com.sba301.chatbackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.codec.StringDecoder
import org.springframework.http.codec.json.Jackson2JsonDecoder
import org.springframework.http.codec.json.Jackson2JsonEncoder
import org.springframework.messaging.rsocket.RSocketStrategies
import org.springframework.security.config.annotation.rsocket.EnableRSocketSecurity
import org.springframework.security.config.annotation.rsocket.RSocketSecurity
import org.springframework.security.oauth2.server.resource.authentication.JwtReactiveAuthenticationManager
import org.springframework.security.rsocket.core.PayloadSocketAcceptorInterceptor
import org.springframework.security.rsocket.metadata.BearerTokenAuthenticationEncoder

@Configuration
@EnableRSocketSecurity
class RSocketSecurityConfig {

//    @Bean
//    fun rsocketSecurity(
//        rsocket: RSocketSecurity,
//        authManager: JwtReactiveAuthenticationManager
//    ): PayloadSocketAcceptorInterceptor {
//        return rsocket
//            .authorizePayload { auth ->
//                auth
//                    .setup().permitAll()
////                    .route("chat.join").authenticated()
////                    .route("chat.leave").authenticated()
////                    .route("chat.stream").authenticated()
////                    .route("chat.send").authenticated()
//                    .anyRequest().permitAll()
////                    .anyRequest().authenticated()
//            }
//            .jwt { jwt -> jwt.authenticationManager(authManager) }
//            .build()
//    }


    @Bean
    fun rsocketSecurity(security: RSocketSecurity): PayloadSocketAcceptorInterceptor {
        return security
            .authorizePayload { it.anyExchange().permitAll() }
            .build()
    }

    @Configuration
    class RSocketConfig {
        @Bean
        fun rSocketStrategies(): RSocketStrategies {
            return RSocketStrategies.builder()
                .decoder(StringDecoder.allMimeTypes())
                .encoder(Jackson2JsonEncoder())
                .decoder(Jackson2JsonDecoder())
                .build()
        }
    }


}
