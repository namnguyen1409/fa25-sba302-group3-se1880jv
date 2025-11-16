package com.sba301.chatbackend.entity

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "url_preview_cache")
class UrlPreviewCache (
    @Id val url: String,
    val preview: LinkPreview,
    @Indexed(expireAfter = "1d") val cachedAt: Instant = Instant.now() // TTL 1 ngày
)