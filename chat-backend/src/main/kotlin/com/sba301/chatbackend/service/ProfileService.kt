package com.sba301.chatbackend.service

import com.sba301.chatbackend.dto.CreateProfileRequest
import com.sba301.chatbackend.dto.ProfileResponse
import com.sba301.chatbackend.dto.UpdateProfileRequest
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ProfileService {
    fun createProfile(userId: String, request: CreateProfileRequest): Mono<ProfileResponse>
    fun getUserProfiles(userId: String): Flux<ProfileResponse>
    fun getProfileById(profileId: String): Mono<ProfileResponse>
    fun getDefaultProfile(userId: String): Mono<ProfileResponse>
    fun updateProfile(profileId: String, userId: String, request: UpdateProfileRequest): Mono<ProfileResponse>
    fun deleteProfile(profileId: String, userId: String): Mono<Void>
}