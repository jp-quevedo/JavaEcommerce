package com.javaecommerce.abm.configs;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Java Ecommerce API",
                version = "1.0",
                description = "Cart, client, invoice and product models with CRUD services"
        )
)
public class OpenApi {
}
