package com.sba301.chatbackend.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import java.time.Instant

data class UserResponse(
    val id: String,
    val username: String,
    val roles: Set<String>,
)

data class CreateUserRequest(
    @field:NotBlank(message = "Username is required")
    @field:Size(min = 3, max = 50, message = "Username must be between 3 and 50 characters")
    val username: String,

    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email must be valid")
    val email: String,

    @field:Size(min = 6, message = "Password must be at least 6 characters")
    val password: String? = null, // Optional for admin-created users

    @field:NotBlank(message = "First name is required")
    @field:Size(max = 100, message = "First name must be less than 100 characters")
    val firstName: String,

    @field:NotBlank(message = "Last name is required")
    @field:Size(max = 100, message = "Last name must be less than 100 characters")
    val lastName: String
)

data class UpdateUserRequest(
    val firstName: String?,
    val lastName: String?,
    @field:Email(message = "Email should be valid")
    val email: String?,
    val isActive: Boolean?
)