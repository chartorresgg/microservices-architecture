package com.edu.poli.product_microservice.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.edu.poli.product_microservice.Model.Product;

public interface ProductRepository extends MongoRepository<Product, String> {

}