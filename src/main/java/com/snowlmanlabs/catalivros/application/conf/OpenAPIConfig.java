package com.snowlmanlabs.catalivros.application.conf;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Cat�logo de Livros")
                        .description("API para gerenciamento de cat�logo e aluguel de livros")
                        .version("v1.0"));
    }
}
