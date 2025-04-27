package com.edu.poli.booking_microservice.clients;

import com.edu.poli.booking_microservice.model.ProductResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

@Component
public class ProductClient {

    private final RestTemplate restTemplate;
    private final String productServiceUrl;

    public ProductClient(RestTemplate restTemplate, @Value("${product.service.url}") String productServiceUrl) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }

    public ProductResponse getProductById(String productId) {
        String url = productServiceUrl + "/products/" + productId;
        return restTemplate.getForObject(url, ProductResponse.class);
    }
}