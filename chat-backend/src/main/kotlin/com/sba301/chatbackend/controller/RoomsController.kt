package com.sba301.chatbackend.controller

import com.sba301.chatbackend.dto.CreateChatRoomRequest
import com.sba301.chatbackend.dto.JoinRoomRequest
import com.sba301.chatbackend.service.ChatRoomService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rooms")
class RoomsController(private val rooms: ChatRoomService) {

    // ========== USER'S ROOMS ==========
    @GetMapping("/my")
    fun myRooms(@AuthenticationPrincipal jwt: Jwt) =
        rooms.getUserRooms(jwt.subject)

    // ========== PUBLIC ROOMS ==========
    @GetMapping("/public")
    fun publicRooms() = rooms.getPublicRooms()

    // ========== CREATE / UPDATE ==========
    @PostMapping
    fun create(@AuthenticationPrincipal jwt: Jwt, @RequestBody req: CreateChatRoomRequest) =
        rooms.createRoom(jwt.subject, req)

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: String,
        @AuthenticationPrincipal jwt: Jwt,
        @RequestBody req: CreateChatRoomRequest
    ) = rooms.updateRoom(id, jwt.subject, req)

    // ========== GET / JOIN / LEAVE ==========
    @GetMapping("/{id}")
    fun getRoom(@PathVariable id: String, @AuthenticationPrincipal jwt: Jwt) =
        rooms.getRoomById(id, jwt.subject)

    @PostMapping("/{id}/join")
    fun joinRoom(
        @PathVariable id: String,
        @AuthenticationPrincipal jwt: Jwt,
        @RequestBody req: JoinRoomRequest
    ) = rooms.joinRoom(id, jwt.subject, req)

    @PostMapping("/{id}/leave")
    fun leaveRoom(@PathVariable id: String, @AuthenticationPrincipal jwt: Jwt) =
        rooms.leaveRoom(id, jwt.subject)

    // ========== MEMBERS ==========
    @GetMapping("/{id}/members")
    fun getMembers(@PathVariable id: String) =
        rooms.getRoomMembers(id)

    // ========== ADMIN MANAGEMENT ==========
    @PostMapping("/{id}/admins/{userId}")
    fun addAdmin(
        @PathVariable id: String,
        @PathVariable userId: String,
        @AuthenticationPrincipal jwt: Jwt
    ) = rooms.addAdmin(id, jwt.subject, userId)

    @DeleteMapping("/{id}/admins/{userId}")
    fun removeAdmin(
        @PathVariable id: String,
        @PathVariable userId: String,
        @AuthenticationPrincipal jwt: Jwt
    ) = rooms.removeAdmin(id, jwt.subject, userId)

    // ========== BAN MANAGEMENT ==========
    @PostMapping("/{id}/ban/{userId}")
    fun banUser(
        @PathVariable id: String,
        @PathVariable userId: String,
        @AuthenticationPrincipal jwt: Jwt
    ) = rooms.banUser(id, jwt.subject, userId)

    @DeleteMapping("/{id}/ban/{userId}")
    fun unbanUser(
        @PathVariable id: String,
        @PathVariable userId: String,
        @AuthenticationPrincipal jwt: Jwt
    ) = rooms.unbanUser(id, jwt.subject, userId)
}
