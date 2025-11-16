package com.sba301.chatbackend.controller

import com.sba301.chatbackend.dto.SendMessageRequest
import com.sba301.chatbackend.dto.WebSocketMessage
import com.sba301.chatbackend.dto.WebSocketMessageType
import com.sba301.chatbackend.rsocket.PresenceService
import com.sba301.chatbackend.rsocket.RoomEventBus
import com.sba301.chatbackend.service.MessageService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Controller
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Controller
class ChatRSocketController(
    private val messages: MessageService,
    private val bus: RoomEventBus,
    private val presence: PresenceService,
) {

    private val log = org.slf4j.LoggerFactory.getLogger(javaClass)

    // ================================
    //  GỬI TIN NHẮN
    // ================================
    @MessageMapping("chat.send")
    fun send(@Payload req: SendMessageRequest): Mono<Void> {
        val userId = req.userId ?: "anonymous"
        log.info("💬 Sending message from {}: {}", userId, req)
        return messages.sendMessage(userId, req)
            .doOnError { e -> log.error("❌ Send message failed: {}", e.message) }
            .then()
    }

    // ================================
    //  NHẬN STREAM ROOM
    // ================================
    @MessageMapping("chat.stream")
    fun stream(@Payload req: Map<String, String>): Flux<WebSocketMessage> {
        val roomId = req["roomId"] ?: error("Missing roomId")
        val userId = req["userId"] ?: "anonymous"

        log.info("📡 Stream request from {} in room {}", userId, roomId)
        presence.join(userId, roomId)
        return bus.stream(roomId)
    }

    // ================================
    //  JOIN ROOM
    // ================================
    @MessageMapping("chat.join")
    fun join(@Payload req: Map<String, String>): Mono<Void> = Mono.fromRunnable {
        val roomId = req["roomId"] ?: error("Missing roomId")
        val userId = req["userId"] ?: "anonymous"

        log.info("👋 {} joined room {}", userId, roomId)
        presence.join(userId, roomId)
        bus.publish(
            roomId,
            WebSocketMessage(
                WebSocketMessageType.USER_JOINED_ROOM,
                mapOf("userId" to userId)
            )
        )
    }

    // ================================
    //  LEAVE ROOM
    // ================================
    @MessageMapping("chat.leave")
    fun leave(@Payload req: Map<String, String>): Mono<Void> = Mono.fromRunnable {
        val roomId = req["roomId"] ?: error("Missing roomId")
        val userId = req["userId"] ?: "anonymous"

        log.info("🚪 {} left room {}", userId, roomId)
        presence.leave(userId, roomId)
        bus.publish(
            roomId,
            WebSocketMessage(
                WebSocketMessageType.USER_LEFT_ROOM,
                mapOf("userId" to userId)
            )
        )
    }
}
