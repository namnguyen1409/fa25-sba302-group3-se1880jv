package com.sba301.chatbackend.repository

import com.sba301.chatbackend.entity.UrlPreviewCache
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UrlPreviewCacheRepository: ReactiveMongoRepository<UrlPreviewCache, String> {
}