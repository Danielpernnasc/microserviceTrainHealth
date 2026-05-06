package com.trainday.health_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@EnableMongoAuditing
@SpringBootApplication
public class HealthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthServiceApplication.class, args);
	}

}
