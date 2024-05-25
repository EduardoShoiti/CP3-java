package com.api.appointmentsmanagement.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI OpenApiConfiguration() {
        String schemeName = "basicAuth";
        String scheme = "basic";
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement()
                        .addList(schemeName)).components(new Components()
                        .addSecuritySchemes(
                                schemeName, new SecurityScheme()
                                        .name(schemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .in(SecurityScheme.In.HEADER)
                                        .scheme(scheme)
                        )
                )
                .info(new Info()
                        .title("Checkpoint 03 - Sistema de Gerenciamento de Clínicas Médicas")
                        .version("0.0.1")
                        .description("Aplicação em Java com Spring Boot e Gradle que gerencia um sistema de \n" +
                                "clínicas médicas. A aplicação permitirá que administradores registrem e gerenciem \n" +
                                "clínicas, médicos e pacientes, enquanto pacientes podem marcar consultas e acessar \n" +
                                "suas informações médicas.")
                        .contact(new Contact()
                                .email("techsplinter@gmail.com")
                                .name("Tech Splinter"))
                        .license(new License()));
    }
}
