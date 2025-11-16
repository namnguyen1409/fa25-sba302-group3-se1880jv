package com.sba301.chatbackend.repository

import com.sba301.chatbackend.entity.Profile
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface ProfileRepository : ReactiveMongoRepository<Profile, String> {
    fun findByUserId(userId: String): Flux<Profile>
    fun findByUserIdAndIsDefault(userId: String, isDefault: Boolean): Mono<Profile>
    fun findByIdAndUserId(profileId: String?, userId: String): Mono<Profile>
    fun countByUserId(userId: String): Mono<Long>
}
