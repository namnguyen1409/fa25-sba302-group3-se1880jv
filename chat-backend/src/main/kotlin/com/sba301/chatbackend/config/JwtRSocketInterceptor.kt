package com.sba301.chatbackend.config

import io.rsocket.ConnectionSetupPayload
import io.rsocket.RSocket
import io.rsocket.SocketAcceptor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class JwtRSocketInterceptor(
    private val jwtDecoder: ReactiveJwtDecoder
) : SocketAcceptor {

    override fun accept(
        setup: ConnectionSetupPayload,
        sendingRSocket: RSocket
    ): Mono<RSocket> {
        val token = extractBearerToken(setup)
            ?: return Mono.error(IllegalArgumentException("Missing Bearer token in RSocket metadata"))

        return jwtDecoder.decode(token)
            .flatMap { jwt -> handleAuthenticated(jwt, sendingRSocket) }
            .switchIfEmpty(Mono.error(IllegalArgumentException("Invalid JWT")))
            .onErrorResume { err ->
                Mono.error(IllegalArgumentException("Token verification failed: ${err.message}", err))
            }
    }

    private fun handleAuthenticated(jwt: Jwt, rSocket: RSocket): Mono<RSocket> {
        val subject = jwt.subject ?: return Mono.error(IllegalArgumentException("Missing sub claim"))
        val roles = jwt.getClaimAsStringList("roles") ?: emptyList()

        val authorities = roles.map { SimpleGrantedAuthority(it) }
        val auth: Authentication = UsernamePasswordAuthenticationToken(subject, jwt.tokenValue, authorities)

        // wrap authenticated socket
        return Mono.just(AuthenticatedRSocket(auth, rSocket))
    }

    private fun extractBearerToken(payload: ConnectionSetupPayload): String? {
        val metadataUtf8 = payload.metadataUtf8 ?: return null
        return if (metadataUtf8.startsWith("Bearer ")) metadataUtf8.removePrefix("Bearer ") else null
    }
}
