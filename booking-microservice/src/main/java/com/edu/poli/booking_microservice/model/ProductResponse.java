package com.edu.poli.booking_microservice.model;

/**
 * ProductResponse class represents the response structure for product details.
 * It contains fields for product ID, name, description, and unit price.
 */
public class ProductResponse {

    private String id; // Unique identifier for the product
    private String productName; // Name of the product
    private String productDescription; // Description of the product
    private Double unitPrice; // Price of the product per unit

    /**
     * Constructor for the ProductResponse class.
     */
    public ProductResponse() {
    }

// =============================== Getters and Setters ============================
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