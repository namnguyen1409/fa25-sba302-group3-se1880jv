package com.sba301.chatbackend.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant


@Document(collection = "chat_rooms")
data class ChatRoom(
    @Id
    val id: String? = null,

    var name: String,
    var description: String? = null,
    var type: RoomType = RoomType.PUBLIC,
    var password: String? = null, // Hashed password for private rooms

    @Indexed
    var ownerId: String, // User ID of the room owner

    var memberIds: MutableSet<String> = mutableSetOf(), // Set of user IDs
    var adminIds: MutableSet<String> = mutableSetOf(), // Set of admin user IDs
    var bannedUserIds: MutableSet<String> = mutableSetOf(), // Banned users

    var maxMembers: Int? = null, // Maximum number of members (null = unlimited)
    var isActive: Boolean = true,
    var settings: RoomSettings = RoomSettings(),

    var lastMessageAt: Instant? = null,

    @CreatedDate
    val createdAt: Instant = Instant.now(),

    @LastModifiedDate
    val updatedAt: Instant = Instant.now()
)

enum class RoomType {
    PUBLIC, PRIVATE
}

data class RoomSettings(
    val allowFileUpload: Boolean = true,
    val allowReactions: Boolean = true,
    val allowReplies: Boolean = true,
    val muteNotifications: Boolean = false,
    val readOnlyMode: Boolean = false
)
