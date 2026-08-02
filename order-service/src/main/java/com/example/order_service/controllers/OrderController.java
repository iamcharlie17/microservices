package com.example.order_service.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.models.Order;
import com.example.order_service.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/")
    public Order save(@RequestBody Order order) {
        return orderService.save(order);
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable String id) {
        return orderService.findById(id);
    }
}
