package com.sba301.chatbackend.config

import io.rsocket.core.RSocketServer
import io.rsocket.frame.decoder.PayloadDecoder
import org.springframework.boot.rsocket.server.RSocketServerCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.rsocket.RSocketStrategies
import org.springframework.messaging.rsocket.annotation.support.RSocketMessageHandler

@Configuration
class RSocketServerConfig {
    @Bean
    fun rSocketMessageHandler(strategies: RSocketStrategies) =
        RSocketMessageHandler().apply {
            rSocketStrategies = strategies
        }

    @Bean
    fun rSocketServerCustomizer(strategies: RSocketStrategies) =
        RSocketServerCustomizer { server: RSocketServer ->
            server.payloadDecoder(PayloadDecoder.ZERO_COPY)
        }

}