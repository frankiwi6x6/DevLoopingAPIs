package com.DEVLooping.answerAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;

@SpringBootApplication
public class AnswerAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(AnswerAPIApplication.class, args);


	}
	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
		.title("Devlooping | API de Respuestas")
		.version("1.0")
		.description("Documentación para la API de gestión de respuestas a los desafíos de Devlooping"));
	}

	@Bean
	public WebMvcConfigurer corsConfigurer(){
		return new WebMvcConfigurer(){
			@Override
			public void addCorsMappings(CorsRegistry registry){
				registry.addMapping("/api/**")
				.allowedOrigins("http://localhost:5173")
				.allowedMethods("*")
				.allowedHeaders("*");
			}
		};
	}

}
