package com.sba301.chatbackend.service

import com.sba301.chatbackend.dto.AttachmentUploadCompleteRequest
import com.sba301.chatbackend.dto.MessageResponse
import com.sba301.chatbackend.dto.SendMessageRequest
import com.sba301.chatbackend.entity.Message
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface MessageService {
    fun sendMessage(userId: String, request: SendMessageRequest): Mono<MessageResponse>
    fun updateAttachment(request: AttachmentUploadCompleteRequest): Mono<MessageResponse>
    fun getMessages(roomId: String, page: Int, size: Int): Flux<MessageResponse>
    fun deleteMessage(userId: String, messageId: String): Mono<Void>
    fun addReaction(userId: String, profileId: String, messageId: String, emoji: String): Mono<MessageResponse>
    fun removeReaction(userId: String, messageId: String, emoji: String): Mono<MessageResponse>
    fun convertToResponse(message: Message): Mono<MessageResponse>
    fun getMessagesBefore(roomId: String, beforeMessageId: String?, limit: Int): Flux<MessageResponse>
}