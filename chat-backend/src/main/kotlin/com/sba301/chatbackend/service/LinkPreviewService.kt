package com.sba301.chatbackend.service

import com.sba301.chatbackend.entity.LinkPreview
import com.sba301.chatbackend.service.impl.LinkPreviewServiceImpl
import reactor.core.publisher.Mono

interface LinkPreviewService {
    fun fetchPreviews(urls: List<String>): Mono<List<LinkPreview>>
    fun processLinks(urls: List<String>): Mono<LinkPreviewServiceImpl.LinkProcessResult>
    fun detectUrls(text: String?, max: Int = 10): List<String>
}