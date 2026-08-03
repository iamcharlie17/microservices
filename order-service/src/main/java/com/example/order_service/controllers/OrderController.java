package com.example.order_service.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.order_service.Constants;
import com.example.order_service.models.Order;
import com.example.order_service.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/")
    public Order save(@RequestBody Order order) {
        Order savedOrder = orderService.save(order);

        // Publish an order event to RabbitMQ after saving
        Map<String, Object> orderEvent = new HashMap<>();
        orderEvent.put("orderId", savedOrder.getId());
        orderEvent.put("productId", savedOrder.getProductId());
        orderEvent.put("customerId", savedOrder.getCustomerId());
        orderEvent.put("quantity", savedOrder.getQuantity());
        orderEvent.put("employeeId", savedOrder.getEmployeeId());
        orderEvent.put("date", savedOrder.getDate());

        rabbitTemplate.convertAndSend(Constants.EXCHANGE, Constants.ROUTING_KEY, orderEvent);
        System.out.println("Order event published to RabbitMQ: " + orderEvent);

        return savedOrder;
    }

    @GetMapping("/{id}")
    public Order findById(@PathVariable String id) {
        return orderService.findById(id);
    }
}
