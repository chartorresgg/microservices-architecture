package com.edu.poli.product_microservice.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu.poli.product_microservice.Model.Product;
import com.edu.poli.product_microservice.repositories.ProductRepository;

/**
 * ProductService class responsible for handling product-related operations.
 * It interacts with the ProductRepository to perform CRUD operations.
 */
@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository; // Repository for product data

    /**
     * Method to retrieve all products from the database.
     * @return
     */
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    /**
     * Method to retrieve a product by its ID.
     * @param id the ID of the product to be retrieved
     * @return the product with the specified ID, or null if not found
     */
    public Product getProductById(String id) {
        return productRepository.findById(id).orElse(null);
    }

    /**
     * Method to retrieve a product by its name.
     * @param productName the name of the product to be retrieved
     * @return the product with the specified name, or null if not found
     */
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    /**
     * Method to retrieve a product by its name.
     * @param productName the name of the product to be retrieved
     * @return the product with the specified name, or null if not found
     */
    public Product updateProduct(Product product) {
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        if (existingProduct != null) {
            existingProduct.setProductName(product.getProductName());
            existingProduct.setProductDescription(product.getProductDescription());
            existingProduct.setUnitPrice(product.getUnitPrice());

            return productRepository.save(existingProduct);
        }
        return null;
    }

    /**
     * Method to delete a product by its ID.
     * @param id the ID of the product to be deleted
     * @return the deleted product, or null if not found
     */
    public Product deleteProduct(String id) {
        Product existingProduct = productRepository.findById(id).orElse(null);
        if (existingProduct != null) {
            productRepository.delete(existingProduct);
            return existingProduct;
        }
        return null;
    }

}
