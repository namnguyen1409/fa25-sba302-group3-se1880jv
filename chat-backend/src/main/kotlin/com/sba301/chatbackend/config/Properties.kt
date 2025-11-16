package com.sba301.chatbackend.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component
import org.springframework.util.unit.DataSize

@Component
@ConfigurationProperties(prefix = "app.file")
data class FileProperties(
    var uploadDir: String = "uploads",
    var maxFileSize: DataSize = DataSize.ofMegabytes(10) // 10MB
)

@Component
@ConfigurationProperties(prefix = "app.chat")
data class ChatProperties(
    var maxMessageLength: Int = 4000,
    var maxProfilesPerUser: Int = 5,
    var defaultPageSize: Int = 50
)

@Component
@ConfigurationProperties(prefix = "app.security")
data class SecurityProperties(
    var allowedOrigins: List<String> = emptyList(),
    var allowedMethods: List<String> = emptyList(),
    var allowedHeaders: List<String> = emptyList(),
    var allowedCredentials: Boolean = true,
    var publicEndpoints: List<String> = emptyList(),
    var jwkSetUri: String = "https://backend.sba301.io.vn/.well-known/jwks.json"
)


@Component
@ConfigurationProperties(prefix = "app.chat.message")
data class MessageProperties(
    var deleteMessageContent: String = "This message has been deleted.",
)

@Component
@ConfigurationProperties(prefix = "minio")
data class MinioProperties(
    var endpoint: String = "",
    var accessKey: String = "",
    var secretKey: String = "",
    var bucket: String = "",
    var secure: Boolean = false
)