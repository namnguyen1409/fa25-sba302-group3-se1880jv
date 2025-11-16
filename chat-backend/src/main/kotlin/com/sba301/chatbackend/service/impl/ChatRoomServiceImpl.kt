package com.sba301.chatbackend.service.impl

import com.sba301.chatbackend.dto.ChatRoomResponse
import com.sba301.chatbackend.dto.CreateChatRoomRequest
import com.sba301.chatbackend.dto.JoinRoomRequest
import com.sba301.chatbackend.dto.RoomMembersResponse
import com.sba301.chatbackend.dto.UserInfo
import com.sba301.chatbackend.entity.ChatRoom
import com.sba301.chatbackend.entity.RoomType
import com.sba301.chatbackend.entity.User
import com.sba301.chatbackend.repository.ChatRoomRepository
import com.sba301.chatbackend.repository.UserChatStatusRepository
import com.sba301.chatbackend.repository.UserRepository
import com.sba301.chatbackend.service.ChatRoomService
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import reactor.kotlin.core.util.function.component1
import reactor.kotlin.core.util.function.component2
import reactor.kotlin.core.util.function.component3

@Service
class ChatRoomServiceImpl(
    private val chatRoomRepository: ChatRoomRepository,
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val userChatStatusRepository: UserChatStatusRepository
) : ChatRoomService {



    @CacheEvict(cacheNames = ["rooms", "publicRooms"], allEntries = true)
    override fun createRoom(ownerId: String, request: CreateChatRoomRequest): Mono<ChatRoomResponse> {
        val room = ChatRoom(
            name = request.name,
            description = request.description,
            type = request.type,
            password = request.password?.let { passwordEncoder.encode(it) },
            ownerId = ownerId,
            memberIds = mutableSetOf(ownerId),
            adminIds = mutableSetOf(ownerId),
            maxMembers = request.maxMembers
        )
        return chatRoomRepository.save(room).map { it.toResponse(ownerId, 0) }
    }


    override fun getUserRooms(userId: String): Flux<ChatRoomResponse> =
        chatRoomRepository.findByMemberIdsContainingAndIsActive(userId, true)
            .sort(compareByDescending<ChatRoom> { it.lastMessageAt }.thenByDescending { it.createdAt })
            .flatMap { room ->
                userChatStatusRepository.findByUserIdAndRoomId(userId, room.id!!)
                    .map { status -> room.toResponse(userId, status.unreadCount) }
                    .defaultIfEmpty(room.toResponse(userId, 0))
            }


    @Cacheable(cacheNames = ["publicRooms"])
    override fun getPublicRooms(): Flux<ChatRoomResponse> =
        chatRoomRepository.findByTypeAndIsActive(RoomType.PUBLIC, true).map { it.toResponse() }


    @Cacheable(cacheNames = ["rooms"], key = "#roomId + '-' + #userId")
    override fun getRoomById(roomId: String, userId: String): Mono<ChatRoomResponse> {
        val roomMono = chatRoomRepository.findById(roomId).filter { it.isActive }
        val statusMono = userChatStatusRepository.findByUserIdAndRoomId(userId, roomId)
            .map { it.unreadCount }.defaultIfEmpty(0)
        return Mono.zip(roomMono, statusMono).map { (room, unread) -> room.toResponse(userId, unread) }
    }


    @CacheEvict(cacheNames = ["rooms", "publicRooms"], allEntries = true)
    override fun joinRoom(roomId: String, userId: String, request: JoinRoomRequest): Mono<ChatRoomResponse> =
        chatRoomRepository.findById(roomId)
            .filter { room ->
                room.isActive && !room.bannedUserIds.contains(userId) &&
                        (room.maxMembers == null || room.memberIds.size < room.maxMembers!!)
            }
            .switchIfEmpty(Mono.error(IllegalAccessException("Cannot join room")))
            .flatMap { room ->
                when {
                    room.type == RoomType.PRIVATE && room.password != null -> {
                        if (request.password == null || !passwordEncoder.matches(request.password, room.password)) {
                            return@flatMap Mono.error(IllegalArgumentException("Invalid room password"))
                        }
                    }
                }
                room.memberIds.add(userId)
                chatRoomRepository.save(room)
            }
            .map { it.toResponse(userId, 0) }

    @CacheEvict(cacheNames = ["rooms", "publicRooms"], allEntries = true)
    override fun leaveRoom(roomId: String, userId: String): Mono<Void> =
        chatRoomRepository.findById(roomId)
            .filter { it.memberIds.contains(userId) }
            .flatMap { room ->
                room.memberIds.remove(userId)
                room.adminIds.remove(userId)
                if (room.ownerId == userId && room.adminIds.isEmpty()) chatRoomRepository.delete(room)
                else chatRoomRepository.save(room).then()
            }

    @CacheEvict(cacheNames = ["rooms", "publicRooms"], allEntries = true)
    override fun updateRoom(roomId: String, ownerId: String, request: CreateChatRoomRequest): Mono<ChatRoomResponse> =
        chatRoomRepository.findById(roomId)
            .filter { it.ownerId == ownerId }
            .switchIfEmpty(Mono.error(IllegalAccessException("Not room owner")))
            .flatMap { room ->
                room.apply {
                    name = request.name
                    description = request.description ?: description
                    type = request.type
                    password = request.password?.let { passwordEncoder.encode(it) } ?: password
                    maxMembers = request.maxMembers ?: maxMembers
                }
                chatRoomRepository.save(room)
            }
            .map { it.toResponse(ownerId, 0) }

    @CacheEvict(cacheNames = ["rooms", "publicRooms"], allEntries = true)
    override fun addAdmin(roomId: String, ownerId: String, userId: String): Mono<Void> =
        chatRoomRepository.findById(roomId)
            .filter { it.ownerId == ownerId && it.memberIds.contains(userId) }
            .switchIfEmpty(Mono.error(IllegalAccessException("Not allowed")))
            .flatMap { room -> room.adminIds.add(userId); chatRoomRepository.save(room) }
            .then()

    @CacheEvict(cacheNames = ["rooms", "publicRooms"], allEntries = true)
    override fun removeAdmin(roomId: String, ownerId: String, userId: String): Mono<Void> =
        chatRoomRepository.findById(roomId)
            .filter { it.ownerId == ownerId && userId != ownerId }
            .switchIfEmpty(Mono.error(IllegalAccessException("Not allowed")))
            .flatMap { room -> room.adminIds.remove(userId); chatRoomRepository.save(room) }
            .then()

    @CacheEvict(cacheNames = ["rooms", "publicRooms"], allEntries = true)
    override fun banUser(roomId: String, adminId: String, userId: String): Mono<Void> =
        chatRoomRepository.findById(roomId)
            .filter { it.adminIds.contains(adminId) && userId != it.ownerId }
            .switchIfEmpty(Mono.error(IllegalAccessException("Not allowed")))
            .flatMap { room ->
                room.memberIds.remove(userId); room.adminIds.remove(userId); room.bannedUserIds.add(userId)
                chatRoomRepository.save(room)
            }
            .then()

    @CacheEvict(cacheNames = ["rooms", "publicRooms"], allEntries = true)
    override fun unbanUser(roomId: String, adminId: String, userId: String): Mono<Void> =
        chatRoomRepository.findById(roomId)
            .filter { it.adminIds.contains(adminId) }
            .switchIfEmpty(Mono.error(IllegalAccessException("Not allowed")))
            .flatMap { room -> room.bannedUserIds.remove(userId); chatRoomRepository.save(room) }
            .then()

    override fun getRoomMembers(roomId: String): Mono<RoomMembersResponse> =
        chatRoomRepository.findById(roomId).flatMap { room ->
            val ownerMono = userRepository.findById(room.ownerId)
            val adminFlux = userRepository.findAllById(room.adminIds)
            val memberFlux = userRepository.findAllById(room.memberIds)
            Mono.zip(ownerMono, adminFlux.collectList(), memberFlux.collectList())
                .map { (owner, admins, members) ->
                    RoomMembersResponse(
                        owner = owner.toUserInfo(),
                        admins = admins.map { it.toUserInfo() },
                        members = members.map { it.toUserInfo() }
                    )
                }
        }

    private fun ChatRoom.toResponse(userId: String? = null, unread: Int = 0) = ChatRoomResponse(
        id = id!!,
        name = name,
        description = description,
        type = type,
        ownerId = ownerId,
        memberCount = memberIds.size,
        isActive = isActive,
        hasPassword = password != null,
        canJoin = userId?.let { !bannedUserIds.contains(it) && (maxMembers == null || memberIds.size < maxMembers!!) } ?: false,
        createdAt = createdAt,
        unread = unread
    )
    private fun User.toUserInfo() = UserInfo(
        id = id!!,
        username = username,
        fullName = fullName,
        email = email,
        roles = roles
    )
}