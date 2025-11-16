package com.sba301.chatbackend.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

/**
 * Message entity representing chat messages
 * Supports text messages, file attachments, replies, and reactions
 */
@Document(collection = "messages")
@CompoundIndex(def = "{'roomId': 1, 'createdAt': -1}", name = "room_created_idx")
data class Message(
    @Id
    val id: String? = null,

    @Indexed
    val roomId: String,

    @Indexed
    val senderId: String,
    val senderProfileId: String,

    val content: String? = null,
    val messageType: MessageType = MessageType.TEXT,

    val attachments: List<FileAttachment> = emptyList(),
    var linkPreviews: List<LinkPreview> = emptyList(),
    var embedCards: List<EmbedCard> = emptyList(),
    val replyToMessageId: String? = null,

    val isDeleted: Boolean = false,
    val deletedAt: Instant? = null,

    val reactions: MutableList<MessageReaction> = mutableListOf(),

    @CreatedDate
    val createdAt: Instant = Instant.now()
)


data class LinkPreview(
    val url: String,
    val finalUrl: String? = null,      // sau redirect
    val siteName: String? = null,
    val title: String? = null,
    val description: String? = null,
    val image: String? = null,         // og:image
    val icon: String? = null,          // favicon
    val contentType: String? = null,   // text/html, video/...
    val fetchedAt: Instant = Instant.now()
)


data class EmbedCard(
    val provider: String,          // "youtube", "spotify", ...
    val type: String?,             // "video", "rich", "audio"
    val originalUrl: String,
    val embedHtml: String?,        // iframe/html từ oEmbed
    val embedUrl: String?,         // fallback: URL iframe tự dựng
    val width: Int? = null,
    val height: Int? = null,
    val title: String? = null,
    val authorName: String? = null,
    val thumbnailUrl: String? = null,
    val fetchedAt: Instant = Instant.now()
)


enum class MessageType {
    TEXT, FILE, IMAGE, VIDEO, AUDIO
}

data class FileAttachment(
    val id: String? = null,
    val fileName: String,
    val originalFileName: String,
    val url: String,
    val fileSize: Long? = null,
    val mimeType: String? = null,
    val uploadedAt: Instant? = null,
    val status: AttachmentStatus = AttachmentStatus.PENDING
)


enum class AttachmentStatus {
    PENDING,
    UPLOADING,
    COMPLETED,
    FAILED
}

data class MessageReaction(
    val userId: String,
    val profileId: String,
    val count: Int = 1,
    val emoji: String,
    val addedAt: Instant = Instant.now()
)
