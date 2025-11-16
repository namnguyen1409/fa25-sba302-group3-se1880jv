package com.sba301.chatbackend.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

/**
 * DTO for user login request
 */
data class LoginRequest(
    @field:NotBlank(message = "Username or email is required")
    val usernameOrEmail: String,

    @field:NotBlank(message = "Password is required")
    @field:Size(min = 6, message = "Password must be at least 6 characters")
    val password: String
)

/**
 * DTO for user registration request
 */
data class RegisterRequest(
    @field:NotBlank(message = "Username is required")
    @field:Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
    val username: String,

    @field:NotBlank(message = "Email is required")
    @field:Email(message = "Email should be valid")
    val email: String,

    @field:NotBlank(message = "Password is required")
    @field:Size(min = 6, message = "Password must be at least 6 characters")
    val password: String,

    @field:NotBlank(message = "First name is required")
    val firstName: String,

    @field:NotBlank(message = "Last name is required")
    val lastName: String
)

/**
 * DTO for authentication response
 */
data class AuthResponse(
    val accessToken: String,
    val refreshToken: String,
    val tokenType: String = "Bearer",
    val user: UserInfo
)

