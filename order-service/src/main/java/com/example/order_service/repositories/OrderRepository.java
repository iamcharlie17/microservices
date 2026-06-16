package com.example.order_service.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.order_service.models.Order;

public interface OrderRepository extends MongoRepository<Order, String> {
    Order findOrderById(String id);
}
