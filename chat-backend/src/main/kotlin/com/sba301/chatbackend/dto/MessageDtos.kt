package com.sba301.chatbackend.dto

import com.sba301.chatbackend.entity.AttachmentStatus
import com.sba301.chatbackend.entity.EmbedCard
import com.sba301.chatbackend.entity.LinkPreview
import com.sba301.chatbackend.entity.MessageType
import java.time.Instant

/**
 * DTO for incoming message from WebSocket
 */
data class SendMessageRequest(
    val type: String = "message",
    val roomId: String,
    val profileId: String, // Profile to use for sending the message
    val content: String? = null,
    val attachments: List<AttachmentRequest> = emptyList(),
    val replyToMessageId: String? = null,
    val userId: String? = null,
)

/**
 * DTO for file attachment request
 */
data class AttachmentRequest(
    val id: String, // Frontend-generated UUID
    val status: AttachmentStatus = AttachmentStatus.PENDING,
    val originalFileName: String,
    val mimeType: String?,
    val fileSize: Long? = null
)

/**
 * DTO for outgoing message via WebSocket
 */
data class MessageResponse(
    val id: String,
    val roomId: String,
    val senderId: String,
    val senderProfileId: String,
    val senderProfile: ProfileInfo,
    val content: String?,
    val messageType: MessageType,
    val attachments: List<AttachmentResponse> = emptyList(),
    val linkPreviews: List<LinkPreview> = emptyList(),
    val embedCards: List<EmbedCard> = emptyList(),
    val replyToMessageId: String? = null,
    val replyToMessage: MessageResponse? = null,
    val reactions: List<ReactionResponse> = emptyList(),
    val isDeleted: Boolean = false,
    val createdAt: Instant
)

data class ProfileInfo(
    val id: String,
    val displayName: String,
    val avatar: String?
)

data class AttachmentResponse(
    val id: String,
    val fileName: String,
    val originalFileName: String,
    val url: String,
    val fileSize: Long?,
    val mimeType: String?,
    val status: AttachmentStatus,
    val uploadedAt: Instant?
)

data class ReactionResponse(
    val userId: String,
    val profileId: String,
    val emoji: String,
    val count: Int,
    val addedAt: Instant
)

/**
 * DTO for attachment upload completion
 */
data class AttachmentUploadCompleteRequest(
    val type: String = "attachment_upload_complete",
    val attachmentId: String,
    val messageId: String,
    val fileName: String,
    val url: String,
    val fileSize: Long?,
    val uploadedAt: Instant = Instant.now()
)

data class InitializeAttachmentRequest(
    val messageId: String,
    val attachmentId: String,
    val originalFileName: String,
    val mimeType: String?,
    val fileSize: Long?
)

data class InitializeAttachmentResponse(
    val uploadUrl: String?, // if using pre-signed URL
    val attachmentId: String,
    val messageId: String,
    val status: AttachmentStatus = AttachmentStatus.UPLOADING
)

data class CompleteAttachmentUploadRequest(
    val messageId: String,
    val attachmentId: String,
    val fileName: String, // stored filename
    val url: String,
    val fileSize: Long?
)
