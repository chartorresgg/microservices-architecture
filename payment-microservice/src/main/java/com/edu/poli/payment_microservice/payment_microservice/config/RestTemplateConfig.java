package com.edu.poli.payment_microservice.payment_microservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration class for RestTemplate bean.
 * This class provides a RestTemplate bean that can be used for making HTTP requests.
 */
@Configuration
public class RestTemplateConfig {
    
    /**
     * Creates a RestTemplate bean.
     * This method is annotated with @Bean, which indicates that it returns a bean to be managed by the Spring container.
     *
     * @return a new instance of RestTemplate
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}