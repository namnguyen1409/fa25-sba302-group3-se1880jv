package com.sba301.chatbackend.service

import com.sba301.chatbackend.dto.ChatRoomResponse
import com.sba301.chatbackend.dto.CreateChatRoomRequest
import com.sba301.chatbackend.dto.JoinRoomRequest
import com.sba301.chatbackend.dto.RoomMembersResponse
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface ChatRoomService {
    fun createRoom(ownerId: String, request: CreateChatRoomRequest): Mono<ChatRoomResponse>
    fun getUserRooms(userId: String): Flux<ChatRoomResponse>
    fun getPublicRooms(): Flux<ChatRoomResponse>
    fun getRoomById(roomId: String, userId: String): Mono<ChatRoomResponse>
    fun joinRoom(roomId: String, userId: String, request: JoinRoomRequest): Mono<ChatRoomResponse>
    fun leaveRoom(roomId: String, userId: String): Mono<Void>
    fun updateRoom(roomId: String, ownerId: String, request: CreateChatRoomRequest): Mono<ChatRoomResponse>
    fun addAdmin(roomId: String, ownerId: String, userId: String): Mono<Void>
    fun removeAdmin(roomId: String, ownerId: String, userId: String): Mono<Void>
    fun banUser(roomId: String, adminId: String, userId: String): Mono<Void>
    fun unbanUser(roomId: String, adminId: String, userId: String): Mono<Void>
    fun getRoomMembers(roomId: String): Mono<RoomMembersResponse>
}