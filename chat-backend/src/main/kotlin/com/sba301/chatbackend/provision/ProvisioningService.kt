package com.sba301.chatbackend.provision

import com.sba301.chatbackend.dto.ProfileResponse
import com.sba301.chatbackend.entity.Profile
import com.sba301.chatbackend.entity.User
import com.sba301.chatbackend.repository.ProfileRepository
import com.sba301.chatbackend.repository.UserRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
@Service
class ProvisioningService(
    private val userRepository: UserRepository,
    private val profileRepository: ProfileRepository,
) {
    fun ensure(sub: String, email: String?, fullName: String?): Mono<ProfileResponse> {
        return userRepository.findById(sub)
            .switchIfEmpty(userRepository.save(User(id = sub, username = email ?: sub, email = email ?: "", fullName = fullName ?: "User")))
            .flatMap { profileRepository.findByUserIdAndIsDefault(sub, true)
                .switchIfEmpty(profileRepository.save(Profile(userId = sub, displayName = fullName ?: "User", isDefault = true))) }
            .map { it.toResponse() }
    }
    private fun Profile.toResponse() = ProfileResponse(id!!, userId, displayName, avatar, bio, status, isDefault, createdAt)
}