package com.example.myOrderManagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@OpenAPIDefinition
@Configuration
@SecurityScheme(
        name = "token",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class ConfigSpringboot {

    @Bean
    public OpenAPI baseOpenAPI() throws IOException {
        return new OpenAPI()
                .info(new Info().title("Spring Doc").version("1.0.0").description("Spring doc"));
    }


    @Bean
    public GroupedOpenApi authenticationApi(){
        String [] paths = {"/auth/**"};
        return GroupedOpenApi.builder()
                .group("Authentication")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi customerApi(){
        String [] paths = {"/customer/**"};
        return GroupedOpenApi.builder()
                .group("Customer")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi stockApi(){
        String [] paths = {"/stock/**"};
        return GroupedOpenApi.builder()
                .group("Stock")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi orderApi(){
        String [] paths = {"/order/**"};
        return GroupedOpenApi.builder()
                .group("Order")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi ProductApi(){
        String [] paths = {"/product/**"};
        return GroupedOpenApi.builder()
                .group("Product")
                .pathsToMatch(paths)
                .build();
    }

    @Bean
    public GroupedOpenApi ProductOrderApi(){
        String [] paths = {"/productOrder/**"};
        return GroupedOpenApi.builder()
                .group("ProductOrder    ")
                .pathsToMatch(paths)
                .build();
    }
}
