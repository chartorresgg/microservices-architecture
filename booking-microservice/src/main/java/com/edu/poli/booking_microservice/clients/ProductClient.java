package com.edu.poli.booking_microservice.clients;

import com.edu.poli.booking_microservice.model.ProductResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Value;

/**
 * ProductClient class responsible
 * for interacting with the Product Service.
 * It uses RestTemplate to make HTTP requests
 * to the Product Service API.
 */
@Component
public class ProductClient {

    private final RestTemplate restTemplate; // RestTemplate for making HTTP requests
    private final String productServiceUrl; // Base URL for the Product Service

    /**
     * Constructor for ProductClient.
     * @param restTemplate the RestTemplate instance to be used for making HTTP requests
     * @param productServiceUrl the base URL of the Product Service
     */
    public ProductClient(RestTemplate restTemplate, @Value("${product.service.url}") String productServiceUrl) {
        this.restTemplate = restTemplate;
        this.productServiceUrl = productServiceUrl;
    }

    /**
     * Method to retrieve a product by its ID from the Product Service.
     * @param productId the ID of the product to be retrieved
     * @return the ProductResponse object containing product details
     */
    public ProductResponse getProductById(String productId) {
        String url = productServiceUrl + "/products/" + productId;
        return restTemplate.getForObject(url, ProductResponse.class);
    }
}