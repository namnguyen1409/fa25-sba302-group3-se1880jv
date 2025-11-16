package com.sba301.chatbackend.config

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityScheme
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.security.SecurityRequirement
import io.swagger.v3.oas.models.servers.Server
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    scheme = "bearer"
)
class SwaggerConfig {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .info(
                Info()
                    .title("Chat Application API")
                    .version("1.0.0")
                    .contact(
                        Contact()
                            .name("TeleComic Team")
                            .email("namnguyen@gmail.com")
                            .url("https://telecomic.top")
                    )
            )
            .servers(
                listOf(
                    Server()
                        .url("https://backend-v3.telecomic.top")
                        .description("Production server"),
                    Server()
                        .url("http://localhost:9999")
                        .description("Local server")
                )
            )
            .addSecurityItem(SecurityRequirement().addList("bearerAuth"))
            .components(
                Components()
                    .addSecuritySchemes("bearerAuth",
                        io.swagger.v3.oas.models.security.SecurityScheme()
                            .name("bearerAuth")
                            .type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP)
                            .scheme("bearer")
                            .bearerFormat("JWT")
                            .description("JWT token. Format: Bearer <token>")
                    )
            )
    }

}