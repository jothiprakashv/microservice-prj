package com.kce.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition(security = @SecurityRequirement(name = "JWT Access Token"))
@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customOpenAPI() {
		return new OpenAPI()
				.info(new Info()
						.title("Ice Cream CRUD API")
						.description("API docs for Ice Cream CRUD operations")
						.version("1.0.0")
						.contact(new Contact()
								.name("Developer Support")
								.email("support@example.com")
								.url("https://example.com"))
						.license(new License()
								.name("Apache 2.0")
								.url("some url")))
				.externalDocs(new ExternalDocumentation()
						.description("Ice Cream Docs")
						.url("some url"));
	}
}







