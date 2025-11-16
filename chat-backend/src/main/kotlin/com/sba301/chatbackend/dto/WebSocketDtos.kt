package com.sba301.chatbackend.dto

import java.time.LocalDateTime

data class WebSocketMessage(
    val type: WebSocketMessageType,
    val data: Any,
    val timestamp: LocalDateTime = LocalDateTime.now()
)

enum class WebSocketMessageType {
    NEW_MESSAGE,
    MESSAGE_DELETED,
    REACTION_ADDED,
    REACTION_REMOVED,
    USER_JOINED_ROOM,
    USER_LEFT_ROOM,
    TYPING_INDICATOR,
    USER_STATUS_CHANGED,
    UPLOAD_STARTED,
    UPLOAD_PROGRESS,
    UPLOAD_COMPLETED,
    UPLOAD_FAILED,
    MESSAGE_UPDATED,
    ATTACHMENT_UPDATED,
    USER_PRESENCE_UPDATE,
    UNREAD_BADGE,
    UNREAD_SYNC
}
