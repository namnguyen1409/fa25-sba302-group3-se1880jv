package com.sba301.chatbackend.rsocket

import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class PresenceService(private val bus: RoomEventBus) {
    private val userRooms = ConcurrentHashMap<String, MutableSet<String>>()
    fun join(userId: String, roomId: String) { userRooms.computeIfAbsent(userId) { mutableSetOf() }.add(roomId) }
    fun leave(userId: String, roomId: String) { userRooms[userId]?.remove(roomId) }
}
