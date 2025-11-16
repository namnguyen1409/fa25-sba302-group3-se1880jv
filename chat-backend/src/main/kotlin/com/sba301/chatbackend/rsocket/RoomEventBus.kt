package com.sba301.chatbackend.rsocket

import com.sba301.chatbackend.dto.WebSocketMessage
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Sinks
import java.util.concurrent.ConcurrentHashMap

@Component
class RoomEventBus {
    private val sinks = ConcurrentHashMap<String, Sinks.Many<WebSocketMessage>>()
    fun stream(roomId: String): Flux<WebSocketMessage> = sinks.computeIfAbsent(roomId) { Sinks.many().multicast().directAllOrNothing() }.asFlux()
    fun publish(roomId: String, msg: WebSocketMessage) { sinks.computeIfAbsent(roomId) { Sinks.many().multicast().directAllOrNothing() }.tryEmitNext(msg) }
}