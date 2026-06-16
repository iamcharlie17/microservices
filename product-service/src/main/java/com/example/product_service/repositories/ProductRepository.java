package com.example.product_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.product_service.models.Product;

public interface ProductRepository extends MongoRepository<Product, String> {
    Product findProductById(String id);
}
