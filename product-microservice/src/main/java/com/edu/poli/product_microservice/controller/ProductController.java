package com.edu.poli.product_microservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.edu.poli.product_microservice.Model.Product;
import com.edu.poli.product_microservice.services.ProductService;

/**
 * ProductController class responsible
 * for handling HTTP requests related to products.
 * It provides endpoints for CRUD operations.
 */
@RestController // Indicates that this class is a REST controller
@RequestMapping("/api/products") // Base URL for product-related endpoints
public class ProductController {

    @Autowired
    private ProductService productService; // Service for product operations

    /**
     * Method to retrieve all products.
     * @return List of all products
     */
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    /**
     * Method to retrieve a product by its ID.
     * @param id the ID of the product to be retrieved
     * @return the product with the specified ID, or null if not found
     */
    @GetMapping("{id}")
    public ResponseEntity<Product> getProductById(@PathVariable String id) {
        Product product = productService.getProductById(id);
        if (product != null) {
            return ResponseEntity.ok(product);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Method to save a new product.
     * @param product the product to be saved
     * @return the saved product
     */
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product createdProduct = productService.saveProduct(product);
        return ResponseEntity.ok(createdProduct);
    }

    /**
     * Method to update an existing product.
     * @param id the ID of the product to be updated
     * @param product the updated product data
     * @return the updated product, or null if not found
     */
    @PutMapping("{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable String id, @RequestBody Product product) {
        product.setId(id);
        Product updatedProduct = productService.updateProduct(product);
        if (updatedProduct != null) {
            return ResponseEntity.ok(updatedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Method to delete a product by its ID.
     * @param id the ID of the product to be deleted
     * @return the deleted product, or null if not found
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String id) {
        Product deletedProduct = productService.deleteProduct(id);
        if (deletedProduct != null) {
            return ResponseEntity.ok(deletedProduct);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}