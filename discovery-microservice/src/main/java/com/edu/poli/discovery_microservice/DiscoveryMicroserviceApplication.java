package com.edu.poli.discovery_microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * Main application class for the Discovery Microservice.
 * This class serves as the entry point for the Spring Boot application.
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(DiscoveryMicroserviceApplication.class, args);
	}

}
