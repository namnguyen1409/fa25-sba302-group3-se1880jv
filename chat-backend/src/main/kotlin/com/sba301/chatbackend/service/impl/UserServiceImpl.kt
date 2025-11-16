package com.sba301.chatbackend.service.impl

import com.sba301.chatbackend.dto.UpdateUserRequest
import com.sba301.chatbackend.dto.UserResponse
import com.sba301.chatbackend.entity.User
import com.sba301.chatbackend.repository.UserRepository
import com.sba301.chatbackend.service.UserService
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    @Cacheable(cacheNames = ["users"], key = "#userId")
    override fun getUserById(userId: String): Mono<UserResponse> =
        userRepository.findById(userId)
            .switchIfEmpty(Mono.error(IllegalArgumentException("User not found: $userId")))
            .map { it.toResponse() }



    override fun deleteUser(userId: String): Mono<Void> =
        userRepository.deleteById(userId)

    private fun User.toResponse() = UserResponse(
        id = id!!,
        username = username,
        roles = roles
    )
}