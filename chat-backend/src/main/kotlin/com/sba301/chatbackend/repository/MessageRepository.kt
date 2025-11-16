package com.sba301.chatbackend.repository

import com.sba301.chatbackend.entity.Message
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.Query
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface MessageRepository : ReactiveMongoRepository<Message, String> {
    fun findByRoomIdOrderByCreatedAtDesc(roomId: String, pageable: Pageable): Flux<Message>
    fun findByRoomIdAndIsDeleted(roomId: String, isDeleted: Boolean): Flux<Message>
    fun findBySenderIdAndIsDeleted(senderId: String, isDeleted: Boolean): Flux<Message>
    fun findByReplyToMessageId(replyToMessageId: String): Flux<Message>
    fun countByRoomIdAndIsDeleted(roomId: String, isDeleted: Boolean): Mono<Long>

    @Query("{ 'attachments.id': ?0 }")
    fun findByAttachmentId(attachmentId: String): Mono<Message>

    fun findByRoomIdAndIdLessThanOrderByCreatedAtDesc(
        roomId: String,
        id: String,
        pageable: Pageable
    ): Flux<Message>
}
