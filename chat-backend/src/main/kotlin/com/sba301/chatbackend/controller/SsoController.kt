package com.sba301.chatbackend.controller

import com.sba301.chatbackend.dto.ProfileResponse
import com.sba301.chatbackend.provision.ProvisioningService
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
@RequestMapping("/api/sso")
class SsoController(
    private val provisioningService: ProvisioningService
) {

    private val log = LoggerFactory.getLogger(javaClass)

    @GetMapping("/login")
    fun ssoLogin(@AuthenticationPrincipal jwt: Jwt): Mono<ResponseEntity<ProfileResponse>> {
        val sub = jwt.subject
        val email = jwt.getClaimAsString("email")
        val fullName = jwt.getClaimAsString("username") ?: jwt.getClaimAsString("preferred_username")

        log.info("🔐 SSO login via Clinic – sub=$sub, email=$email, name=$fullName")

        return provisioningService.ensure(sub, email, fullName)
            .map { ResponseEntity.ok(it) }
    }
}