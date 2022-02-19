package com.springMicroservice.VehicleApplicationAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;

@Configuration
@SpringBootApplication
public class VehicleApplicationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(VehicleApplicationApiApplication.class, args);
	}

	public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
		configurer.favorParameter(false).
				ignoreAcceptHeader(true).
				defaultContentType(MediaType.APPLICATION_JSON);
	}



}
