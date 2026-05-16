package tech.gtech.user.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("User Microservice API")
                        .version("1.0.0")
                        .description("Documentação dos endpoints do microsserviço de usuários integrado com envio de emails (RabbitMQ)."));
    }
}