package com.sba301.chatbackend.service.impl

import com.sba301.chatbackend.config.ChatProperties
import com.sba301.chatbackend.dto.CreateProfileRequest
import com.sba301.chatbackend.dto.ProfileResponse
import com.sba301.chatbackend.dto.UpdateProfileRequest
import com.sba301.chatbackend.entity.Profile
import com.sba301.chatbackend.repository.ProfileRepository
import com.sba301.chatbackend.service.ProfileService
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class ProfileServiceImpl(
    private val chatProperties: ChatProperties,
    private val profileRepository: ProfileRepository
): ProfileService {
    private val log = LoggerFactory.getLogger(javaClass)


    override fun createProfile(userId: String, request: CreateProfileRequest): Mono<ProfileResponse> =
        profileRepository.countByUserId(userId)
            .flatMap { count ->
                if (count >= chatProperties.maxProfilesPerUser) {
                    Mono.error(IllegalStateException("Max profiles reached"))
                } else {
                    profileRepository.save(
                        Profile(
                            userId = userId,
                            displayName = request.displayName,
                            avatar = request.avatar,
                            bio = request.bio,
                            isDefault = if (count == 0L) true else request.isDefault
                        )
                    )
                }
            }
            .map { it.toResponse() }


    override fun getUserProfiles(userId: String): Flux<ProfileResponse> =
        profileRepository.findByUserId(userId).map { it.toResponse() }


    override fun getProfileById(profileId: String): Mono<ProfileResponse> =
        profileRepository.findById(profileId).map { it.toResponse() }


    override fun getDefaultProfile(userId: String): Mono<ProfileResponse> =
        profileRepository.findByUserIdAndIsDefault(userId, true).map { it.toResponse() }


    override fun updateProfile(profileId: String, userId: String, request: UpdateProfileRequest): Mono<ProfileResponse> =
        profileRepository.findById(profileId)
            .filter { it.userId == userId }
            .switchIfEmpty(Mono.error(IllegalAccessException("Not owner of profile")))
            .flatMap { p ->
                p.apply {
                    displayName = request.displayName ?: displayName
                    avatar = request.avatar ?: avatar
                    bio = request.bio ?: bio
                    status = request.status ?: status
                }
                profileRepository.save(p)
            }
            .map { it.toResponse() }


    override fun deleteProfile(profileId: String, userId: String): Mono<Void> =
        profileRepository.findById(profileId)
            .filter { it.userId == userId && !it.isDefault }
            .switchIfEmpty(Mono.error(IllegalStateException("Cannot delete default / not owner")))
            .flatMap { profileRepository.delete(it) }


    private fun Profile.toResponse() = ProfileResponse(
        id = id!!, userId = userId, displayName = displayName,
        avatar = avatar, bio = bio, status = status, isDefault = isDefault,
        createdAt = createdAt
    )
}