package com.example.inventory_service.controllers;

import java.util.Map;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory_service.Constants;
import com.example.inventory_service.models.Product;
import com.example.inventory_service.services.InventoryService;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * RabbitMQ consumer: listens on rabbit_queue.
     * When an order event arrives, checks stock and either fulfils or rejects it.
     */
    @RabbitListener(queues = Constants.QUEUE)
    public void consumeMessageFromQueue(Map<String, Object> orderEvent) {
        System.out.println("Message Received from queue: " + orderEvent);

        String productId = (String) orderEvent.get("productId");
        int quantity = (int) orderEvent.get("quantity");

        String result = inventoryService.processOrder(productId, quantity);
        System.out.println("[Inventory] " + result);
    }

    /**
     * REST endpoint to view the current in-memory stock (useful for testing).
     * GET http://localhost:8080/inventory/stock  (via API Gateway, if route is added)
     * GET http://localhost:8085/inventory/stock  (direct)
     */
    @GetMapping("/stock")
    public Map<String, Product> getStock() {
        return inventoryService.getAllProducts();
    }
}
