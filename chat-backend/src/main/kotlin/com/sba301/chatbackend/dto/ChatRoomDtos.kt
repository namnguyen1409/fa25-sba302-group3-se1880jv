package com.sba301.chatbackend.dto

import com.sba301.chatbackend.entity.RoomType
import jakarta.validation.constraints.Max
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.Instant

data class ChatRoomResponse(
    val id: String,
    val name: String,
    val description: String?,
    val type: RoomType,
    val ownerId: String,
    val memberCount: Int,
    val isActive: Boolean,
    val hasPassword: Boolean,
    val canJoin: Boolean,
    val createdAt: Instant,
    val unread: Int = 0
)

data class CreateChatRoomRequest(
    @field:NotBlank(message = "Room name is required")
    @field:Size(min = 2, max = 100, message = "Room name must be between 2 and 100 characters")
    val name: String,

    @field:Size(max = 500, message = "Description must be less than 500 characters")
    val description: String? = null,

    val type: RoomType = RoomType.PUBLIC,

    @field:Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
    val password: String? = null,

    @field:Min(value = 2, message = "Max members must be at least 2")
    @field:Max(value = 1000, message = "Max members must be at most 1000")
    val maxMembers: Int? = null
)

data class JoinRoomRequest(
    val password: String? = null
)

data class UserInfo(
    val id: String,
    val fullName: String,
    val username: String,
    val email: String,
    val roles: Set<String>
)

data class RoomMembersResponse(
    val owner: UserInfo,
    val admins: List<UserInfo>,
    val members: List<UserInfo>
)