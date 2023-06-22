package com.bank.transfer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Имя",
                        email = "Мэйл",
                        url = "http://localhost:8092/api/transfer/swagger-ui/index.html"
                ),
                description = "Какое-то описание",
                title = "Документация по Transfer'у",
                version = "1.0",
                license = @License (
                        name = "Лицезия",
                        url = "http://localhost:8092/api/transfer/swagger-ui/index.html"
                ),
                termsOfService = "Еще описание"
        ),
        servers = @Server(
                description = "Сервер Transfer'а",
                url = "http://localhost:8092"
        )
)
public class SwaggerConfig {
}
