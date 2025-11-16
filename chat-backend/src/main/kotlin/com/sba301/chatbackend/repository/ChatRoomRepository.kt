package com.sba301.chatbackend.repository

import com.sba301.chatbackend.entity.ChatRoom
import com.sba301.chatbackend.entity.RoomType
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface ChatRoomRepository : ReactiveMongoRepository<ChatRoom, String> {
    fun findByTypeAndIsActive(type: RoomType, isActive: Boolean): Flux<ChatRoom>
    fun findByOwnerIdAndIsActive(ownerId: String, isActive: Boolean): Flux<ChatRoom>
    fun findByMemberIdsContainingAndIsActive(memberId: String, isActive: Boolean): Flux<ChatRoom>
    fun findByNameContainingIgnoreCaseAndTypeAndIsActive(name: String, type: RoomType, isActive: Boolean): Flux<ChatRoom>
}
