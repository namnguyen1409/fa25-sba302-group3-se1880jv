package com.sba301.chatbackend.config

import io.minio.BucketExistsArgs
import io.minio.MakeBucketArgs
import io.minio.MinioClient
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class MinioConfig(
    private val properties: MinioProperties
) {

    @Bean
    fun minioClient(): MinioClient {
        val client = MinioClient.builder()
            .endpoint(properties.endpoint)
            .credentials(properties.accessKey, properties.secretKey)
            .build()

        if (!client.bucketExists(BucketExistsArgs.builder().bucket(properties.bucket).build())) {
            client.makeBucket(MakeBucketArgs.builder().bucket(properties.bucket).build())
            println("✅ Created bucket: ${properties.bucket}")
        } else {
            println("✅ Bucket already exists: ${properties.bucket}")
        }
        return client
    }
}
