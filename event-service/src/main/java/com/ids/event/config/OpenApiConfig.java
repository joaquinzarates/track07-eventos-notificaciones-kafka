package com.ids.event.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI eventServiceOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Event Service API")
                        .description("Registra eventos del sistema y los publica en Kafka (topic system-events)")
                        .version("1.0"));
    }
}