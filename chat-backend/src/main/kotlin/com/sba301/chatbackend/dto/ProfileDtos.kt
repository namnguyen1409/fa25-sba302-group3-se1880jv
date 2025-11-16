package com.sba301.chatbackend.dto

import com.sba301.chatbackend.entity.ProfileStatus
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

data class ProfileResponse(
    val id: String,
    val userId: String,
    val displayName: String,
    val avatar: String?,
    val bio: String?,
    val status: ProfileStatus,
    val isDefault: Boolean,
    val createdAt: LocalDateTime
)

data class CreateProfileRequest(
    @field:NotBlank(message = "Display name is required")
    @field:Size(min = 2, max = 100, message = "Display name must be between 2 and 100 characters")
    val displayName: String,

    @field:Size(max = 500, message = "Avatar URL must be less than 500 characters")
    val avatar: String? = null,

    @field:Size(max = 1000, message = "Bio must be less than 1000 characters")
    val bio: String? = null,

    val isDefault: Boolean = false
)

data class UpdateProfileRequest(
    @field:Size(min = 2, max = 100, message = "Display name must be between 2 and 100 characters")
    var displayName: String?,

    @field:Size(max = 500, message = "Avatar URL must be less than 500 characters")
    var avatar: String?,

    @field:Size(max = 1000, message = "Bio must be less than 1000 characters")
    var bio: String?,

    var status: ProfileStatus?
)
