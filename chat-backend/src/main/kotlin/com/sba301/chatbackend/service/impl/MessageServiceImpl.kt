package com.sba301.chatbackend.service.impl

import com.sba301.chatbackend.config.MessageProperties
import com.sba301.chatbackend.dto.AttachmentResponse
import com.sba301.chatbackend.dto.AttachmentUploadCompleteRequest
import com.sba301.chatbackend.dto.MessageResponse
import com.sba301.chatbackend.dto.ProfileInfo
import com.sba301.chatbackend.dto.ReactionResponse
import com.sba301.chatbackend.dto.SendMessageRequest
import com.sba301.chatbackend.dto.WebSocketMessage
import com.sba301.chatbackend.dto.WebSocketMessageType
import com.sba301.chatbackend.entity.AttachmentStatus
import com.sba301.chatbackend.entity.FileAttachment
import com.sba301.chatbackend.entity.Message
import com.sba301.chatbackend.entity.MessageReaction
import com.sba301.chatbackend.entity.MessageType
import com.sba301.chatbackend.entity.Profile
import com.sba301.chatbackend.entity.UserChatStatus
import com.sba301.chatbackend.repository.ChatRoomRepository
import com.sba301.chatbackend.repository.MessageRepository
import com.sba301.chatbackend.repository.ProfileRepository
import com.sba301.chatbackend.repository.UserChatStatusRepository
import com.sba301.chatbackend.rsocket.RoomEventBus
import com.sba301.chatbackend.service.LinkPreviewService
import com.sba301.chatbackend.service.MessageService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.time.Instant

@Service
class MessageServiceImpl(
    private val messageRepository: MessageRepository,
    private val profileRepository: ProfileRepository,
    private val chatRoomRepository: ChatRoomRepository,
    private val userChatStatusRepository: UserChatStatusRepository,
    private val messageProperties: MessageProperties,
    private val linkPreviewService: LinkPreviewService,
    private val bus: RoomEventBus,
) : MessageService {


    private val log = LoggerFactory.getLogger(javaClass)
    override fun sendMessage(userId: String, request: SendMessageRequest): Mono<MessageResponse>
        {

        log.info("Sending message from user $userId to room ${request.roomId}")
        return validate(userId, request)

            .flatMap { messageRepository.save(request.toMessage(userId)) }
            .flatMap { saved ->
                chatRoomRepository.findById(request.roomId)
                    .flatMap { room -> room.lastMessageAt = saved.createdAt; chatRoomRepository.save(room).thenReturn(saved) }
            }
            .flatMap { saved ->
                toResponse(saved).flatMap { res ->
                    bus.publish(request.roomId, WebSocketMessage(WebSocketMessageType.NEW_MESSAGE, res));
                    unread(request.roomId, userId, res).thenReturn(res)
                }.flatMap { res ->
                    val urls = linkPreviewService.detectUrls(saved.content)
                    if (urls.isEmpty()) Mono.just(res) else linkPreviewService.processLinks(urls)
                        .flatMap { meta -> messageRepository.save(saved.apply { linkPreviews = meta.linkPreviews; embedCards = meta.embedCards }) }
                        .flatMap { upd -> toResponse(upd) }
                        .doOnNext { upd -> bus.publish(request.roomId, WebSocketMessage(WebSocketMessageType.MESSAGE_UPDATED, upd)) }
                        .thenReturn(res)
                }
            }
        }

    private fun validate(userId: String, req: SendMessageRequest): Mono<Profile> =
        profileRepository.findById(req.profileId).filter { it.userId == userId }
            .switchIfEmpty(Mono.error(IllegalArgumentException("Invalid profile")))
            .flatMap { chatRoomRepository.findById(req.roomId).filter { it.memberIds.contains(userId) }
                .switchIfEmpty(Mono.error(IllegalArgumentException("User not in room"))).thenReturn(it) }

    private fun SendMessageRequest.toMessage(userId: String) = Message(
        roomId = roomId, senderId = userId, senderProfileId = profileId, content = content,
        messageType = if (attachments.isEmpty()) MessageType.TEXT else when {
            attachments.any { it.mimeType?.startsWith("image/") == true } -> MessageType.IMAGE
            attachments.any { it.mimeType?.startsWith("video/") == true } -> MessageType.VIDEO
            attachments.any { it.mimeType?.startsWith("audio/") == true } -> MessageType.AUDIO
            else -> MessageType.FILE
        },
        attachments = attachments.map { a -> FileAttachment(id = a.id, fileName = a.id, originalFileName = a.originalFileName, url = "", fileSize = a.fileSize, mimeType = a.mimeType, status = a.status) },
        replyToMessageId = replyToMessageId, createdAt = Instant.now()
    )

    override fun updateAttachment(request: AttachmentUploadCompleteRequest): Mono<MessageResponse> =
        messageRepository.findById(request.messageId)
            .flatMap { m ->
                val updated = m.copy(attachments = m.attachments.map { att ->
                    if (att.id == request.attachmentId) att.copy(fileName = request.fileName, url = request.url, fileSize = request.fileSize, status = AttachmentStatus.COMPLETED, uploadedAt = request.uploadedAt) else att
                }.toMutableList())
                messageRepository.save(updated)
            }
            .flatMap { saved -> toResponse(saved).doOnNext { res -> bus.publish(saved.roomId, WebSocketMessage(WebSocketMessageType.ATTACHMENT_UPDATED, res)) } }

    override fun getMessages(roomId: String, page: Int, size: Int): Flux<MessageResponse> =
        messageRepository.findByRoomIdOrderByCreatedAtDesc(roomId, org.springframework.data.domain.PageRequest.of(page, size)).flatMap { toResponse(it) }

    override fun deleteMessage(userId: String, messageId: String): Mono<Void> =
        messageRepository.findById(messageId)
            .filter { it.senderId == userId }
            .switchIfEmpty(Mono.error(IllegalAccessException("Not owner")))
            .flatMap { msg -> messageRepository.save(msg.copy(isDeleted = true, deletedAt = Instant.now())) }
            .flatMap { saved -> toResponse(saved).doOnNext { res -> bus.publish(saved.roomId, WebSocketMessage(WebSocketMessageType.MESSAGE_DELETED, res)) } }
            .then()

    override fun addReaction(userId: String, profileId: String, messageId: String, emoji: String): Mono<MessageResponse> =
        messageRepository.findById(messageId)
            .flatMap { msg ->
                val existing = msg.reactions.find { it.userId == userId && it.emoji == emoji }
                val updated = if (existing != null) msg.copy(reactions = msg.reactions.map { if (it.userId == userId && it.emoji == emoji) it.copy(count = it.count + 1) else it }.toMutableList())
                else msg.copy(reactions = (msg.reactions + MessageReaction(userId, profileId, 1, emoji)).toMutableList())
                messageRepository.save(updated)
            }
            .flatMap { saved -> toResponse(saved).doOnNext { res -> bus.publish(saved.roomId, WebSocketMessage(WebSocketMessageType.REACTION_ADDED, res)) } }

    override fun removeReaction(userId: String, messageId: String, emoji: String): Mono<MessageResponse> =
        messageRepository.findById(messageId)
            .flatMap { msg -> messageRepository.save(msg.copy(reactions = msg.reactions.filterNot { it.userId == userId && it.emoji == emoji }.toMutableList())) }
            .flatMap { saved -> toResponse(saved).doOnNext { res -> bus.publish(saved.roomId, WebSocketMessage(WebSocketMessageType.REACTION_REMOVED, res)) } }

    override fun convertToResponse(message: Message): Mono<MessageResponse> = toResponse(message)
    override fun getMessagesBefore(
        roomId: String,
        beforeMessageId: String?,
        limit: Int
    ): Flux<MessageResponse> {
        val pageable = PageRequest.of(0, limit)
        return if (beforeMessageId == null)
            messageRepository.findByRoomIdOrderByCreatedAtDesc(roomId, pageable)
                .flatMap { toResponse(it) }
        else
            messageRepository.findByRoomIdAndIdLessThanOrderByCreatedAtDesc(roomId, beforeMessageId, pageable)
                .flatMap { toResponse(it) }
    }

    private fun toResponse(message: Message): Mono<MessageResponse> {
        val replyMono = message.replyToMessageId?.let { messageRepository.findById(it).flatMap(::toResponse) } ?: Mono.empty()
        return profileRepository.findById(message.senderProfileId).flatMap { profile ->
            replyMono.defaultIfEmpty(emptyMessage()).map { reply ->
                MessageResponse(
                    id = message.id!!,
                    roomId = message.roomId,
                    senderId = message.senderId,
                    senderProfileId = message.senderProfileId,
                    senderProfile = ProfileInfo(profile.id!!, profile.displayName, profile.avatar),
                    content = when {
                        message.isDeleted -> messageProperties.deleteMessageContent
                        message.content.isNullOrBlank() -> ""
                        else -> message.content
                    },
                    messageType = message.messageType,
                    attachments = if (message.isDeleted) emptyList() else message.attachments.map { a -> AttachmentResponse(a.id!!, a.fileName, a.originalFileName, a.url, a.fileSize, a.mimeType, a.status, a.uploadedAt) },
                    linkPreviews = if (message.isDeleted) emptyList() else message.linkPreviews,
                    embedCards = if (message.isDeleted) emptyList() else message.embedCards,
                    replyToMessageId = message.replyToMessageId,
                    replyToMessage = reply,
                    reactions = if (message.isDeleted) emptyList() else message.reactions.map { r -> ReactionResponse(r.userId, r.profileId, r.emoji, r.count, r.addedAt) },
                    isDeleted = message.isDeleted,
                    createdAt = message.createdAt
                )
            }
        }
    }

    private fun unread(roomId: String, senderId: String, res: MessageResponse): Mono<Void> =
        chatRoomRepository.findById(roomId)
            .flatMapMany { Flux.fromIterable(it.memberIds) }
            .filter { it != senderId }
            .flatMap { uid ->
                userChatStatusRepository.findByUserIdAndRoomId(uid, roomId)
                    .switchIfEmpty(userChatStatusRepository.save(UserChatStatus(userId = uid, roomId = roomId)))
                    .flatMap { s -> s.unreadCount += 1; userChatStatusRepository.save(s) }
                    .doOnNext { _ -> bus.publish(roomId, WebSocketMessage(WebSocketMessageType.UNREAD_BADGE, mapOf("roomId" to roomId, "userId" to uid))) }
                    .then()
            }
            .then()

    private fun emptyMessage() = MessageResponse(
        id = "", roomId = "", senderId = "", senderProfileId = "",
        senderProfile = ProfileInfo("", "", null),
        content = null,
        messageType = MessageType.TEXT,
        attachments = emptyList(),
        linkPreviews = emptyList(),
        embedCards = emptyList(),
        replyToMessageId = null,
        replyToMessage = null,
        reactions = emptyList(),
        isDeleted = false,
        createdAt = Instant.EPOCH
    )

}