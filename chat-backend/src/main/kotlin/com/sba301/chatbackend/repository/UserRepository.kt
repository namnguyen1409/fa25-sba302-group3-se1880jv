package com.sba301.chatbackend.repository

import com.sba301.chatbackend.entity.User
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono

@Repository
interface UserRepository : ReactiveMongoRepository<User, String> {
    fun findByUsername(username: String): Mono<User>
    fun existsByUsername(username: String): Mono<Boolean>
}
