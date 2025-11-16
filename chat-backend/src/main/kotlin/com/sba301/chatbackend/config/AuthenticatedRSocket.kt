package com.sba301.chatbackend.config

import io.rsocket.Payload
import io.rsocket.RSocket
import org.reactivestreams.Publisher
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.security.Principal

class AuthenticatedRSocket(
    private val principal: Principal,
    private val delegate: RSocket
): RSocket {
    fun principal(): Principal = principal

    override fun requestResponse(payload: Payload): Mono<Payload> {
        return delegate.requestResponse(payload)
    }

    override fun fireAndForget(payload: Payload): Mono<Void> {
        return delegate.fireAndForget(payload)
    }

    override fun requestStream(payload: Payload): Flux<Payload> {
        return delegate.requestStream(payload)
    }

    override fun requestChannel(payloads: Publisher<Payload>): Flux<Payload> {
        return delegate.requestChannel(payloads)
    }

    override fun metadataPush(payload: Payload): Mono<Void> {
        return delegate.metadataPush(payload)
    }

    override fun dispose() {
        delegate.dispose()
    }

    override fun isDisposed(): Boolean = delegate.isDisposed

}