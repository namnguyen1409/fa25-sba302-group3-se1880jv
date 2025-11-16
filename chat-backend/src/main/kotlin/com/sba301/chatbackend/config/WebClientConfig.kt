package com.sba301.chatbackend.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.ExchangeStrategies
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import java.time.Duration

@Configuration
class WebClientConfig {

    @Bean
    fun webClient(): WebClient {
        val exchangeStrategies = ExchangeStrategies.builder()
            .codecs { configurer ->
                configurer.defaultCodecs().maxInMemorySize(5 * 1024 * 1024)
            }
            .build()
        val httpClient = HttpClient.create()
            .compress(true) // Enable response compression
            .followRedirect(true) // Follow redirects automatically
            .responseTimeout(Duration.ofSeconds(10)) // Set a response timeout

        return WebClient.builder()
            .clientConnector(ReactorClientHttpConnector(httpClient))

            .exchangeStrategies(exchangeStrategies)
            .defaultHeaders { headers ->
                headers[HttpHeaders.USER_AGENT] =
                    "Mozilla/5.0 (Windows NT 10.0; Win64; x64) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) " +
                            "Chrome/120.0.0.0 Safari/537.36"

                headers[HttpHeaders.ACCEPT] =
                    "text/html,application/xhtml+xml,application/xml;q=0.9," +
                            "image/avif,image/webp,image/apng,*/*;q=0.8"

                headers[HttpHeaders.ACCEPT_LANGUAGE] = "en-US,en;q=0.9"
                headers["DNT"] = "1"
                headers[HttpHeaders.CONNECTION] = "keep-alive"
                headers["Upgrade-Insecure-Requests"] = "1"
                headers["Sec-Fetch-Dest"] = "document"
                headers["Sec-Fetch-Mode"] = "navigate"
                headers["Sec-Fetch-Site"] = "none"
                headers["Sec-Fetch-User"] = "?1"
                headers[HttpHeaders.CACHE_CONTROL] = "no-cache"
                headers[HttpHeaders.PRAGMA] = "no-cache"
            }
            .build()
    }
}