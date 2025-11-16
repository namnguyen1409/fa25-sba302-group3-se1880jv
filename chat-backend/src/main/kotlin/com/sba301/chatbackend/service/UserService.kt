package com.sba301.chatbackend.service

import com.sba301.chatbackend.dto.UserResponse
import reactor.core.publisher.Mono

interface UserService {
    fun getUserById(userId: String): Mono<UserResponse>
    fun deleteUser(userId: String): Mono<Void>
}