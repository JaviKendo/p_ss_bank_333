package com.bank.authorization.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации Swagger.
 */
@Configuration
@OpenAPIDefinition
public class SwaggerConfig {

    /**
     * Метод создает объект OpenAPI.
     *
     * @return объект OpenAPI с заполненными полями title, version, description.
     * http://localhost:8087/api/authorizations/swagger-ui/index.html#/ - документация SWAGGER
     *
     */
    @Bean
    public OpenAPI baseOpenApi() {
        return new OpenAPI().info(new Info().title("Transfer API").version("1.0.0"));
    }
}

