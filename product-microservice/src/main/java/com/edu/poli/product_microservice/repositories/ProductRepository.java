package com.edu.poli.product_microservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.edu.poli.product_microservice.Model.Product;

/**
 * ProductRepository interface for managing Product entities in the database.
 * It extends MongoRepository to provide CRUD operations and custom query methods.
 */
public interface ProductRepository extends MongoRepository<Product, String> {

}