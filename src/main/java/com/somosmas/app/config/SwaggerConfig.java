package com.somosmas.app.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.somosmas.app.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(getApiInfo());

	}
	
	private ApiInfo getApiInfo() {
		return new ApiInfo(
				"Somos Más API",
				"Grupo 72",
				"1.0",
				"",
				new Contact("Alkemy Labs", "website", "email"),
				"Api License",
				"LICENSE URL",
				Collections.emptyList()
				);
	}
}
