package com.microservices.productmicroservice.repository;

import com.microservices.productmicroservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}