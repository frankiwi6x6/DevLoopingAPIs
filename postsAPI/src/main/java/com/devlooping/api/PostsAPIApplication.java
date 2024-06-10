package com.devlooping.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.devlooping.api.websocket.PostHandler;

import io.swagger.v3.oas.models.OpenAPI;
@SpringBootApplication
public class PostsAPIApplication {

	public static void main(String[] args) {
		SpringApplication.run(PostsAPIApplication.class, args);


	}
	@Bean
	public OpenAPI customOpenAPI(){
		return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
		.title("Devlooping | API de Post")
		.version("1.0")
		.description("Documentación para la API de gestión de publicaciones de Devlooping"));
	}

	@Bean
	public PostHandler postHandler(){
		return new PostHandler();
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
