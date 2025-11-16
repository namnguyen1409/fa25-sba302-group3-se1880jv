package com.sba301.chatbackend.repository

import com.mongodb.client.result.UpdateResult
import com.sba301.chatbackend.entity.UserChatStatus
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Repository
interface UserChatStatusRepository :
    ReactiveMongoRepository<UserChatStatus, String>,
    UserChatStatusRepositoryCustom {

    fun findByUserIdAndRoomId(userId: String, roomId: String): Mono<UserChatStatus>
    fun findByRoomId(roomId: String): Flux<UserChatStatus>
    fun findByRoomIdAndUserIdNot(roomId: String, userId: String): Flux<UserChatStatus>
}

interface UserChatStatusRepositoryCustom {
    fun incUnreadForRoomExceptUser(roomId: String, excludeUserId: String): Mono<UpdateResult>
    fun resetUnreadCountForRoomAndUser(roomId: String, userId: String): Mono<UpdateResult>
}
