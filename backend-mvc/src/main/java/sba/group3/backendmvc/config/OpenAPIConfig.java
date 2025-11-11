package sba.group3.backendmvc.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {


    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                        )
                )
                .servers(
                        List.of(new io.swagger.v3.oas.models.servers.Server().url("http://localhost:9999/"))
                )
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(new Info()
                        .title("Clinic Management API")
                        .version("v1")
                        .description("API documentation for the Clinic Management system"));
    }
}
