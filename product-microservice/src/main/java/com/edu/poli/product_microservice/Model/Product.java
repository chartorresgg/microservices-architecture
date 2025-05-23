package com.edu.poli.product_microservice.Model;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Product class representing a product entity in the database.
 * It contains fields for product ID, name, description, and unit price.
 */
@EntityScan
@Document(value = "product")
public class Product {
    
    @Id
    private String id; // Unique identifier for the product
    private String productName; // Name of the product
    private String productDescription; // Description of the product
    private Double unitPrice; // Price of the product per unit
    /**
     * Constructor for the Product class.
     * @param id the unique identifier for the product
     * @param productName the name of the product
     * @param productDescription the description of the product
     * @param unitPrice the price of the product per unit
     */
    public Product(String id, String productName, String productDescription, Double unitPrice) {
        this.id = id;
        this.productName = productName;
        this.productDescription = productDescription;
        this.unitPrice = unitPrice;
    }

    /**
     * Default constructor for the Product class.
     * This constructor is used by Spring Data MongoDB to create instances of the Product class.
     */
    public Product() {
    }

    // ============================ Getters and Setters ============================
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

}