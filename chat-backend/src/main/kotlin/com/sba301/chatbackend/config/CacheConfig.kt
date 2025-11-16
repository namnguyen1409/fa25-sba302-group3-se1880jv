package com.sba301.chatbackend.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class CacheConfig {
    @Bean
    fun cacheManager(): CacheManager {
        val cacheManager = CaffeineCacheManager()
        cacheManager.setAsyncCacheMode(true)
        cacheManager.setCaffeine(
            Caffeine.newBuilder()
                .expireAfterWrite(Duration.ofMinutes(10))
                .maximumSize(1000)

        )
        return cacheManager
    }
}
