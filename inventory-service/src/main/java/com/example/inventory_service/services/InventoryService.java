package com.example.inventory_service.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.inventory_service.models.Product;

@Service
public class InventoryService {

    // In-memory product store seeded with sample products
    private final Map<String, Product> productStore = new HashMap<>();

    public InventoryService() {
        // Seed with sample products (productId -> Product)
        productStore.put("P001", new Product("P001", "Laptop",     50));
        productStore.put("P002", new Product("P002", "Smartphone", 120));
        productStore.put("P003", new Product("P003", "Headphones", 200));
    }

    /**
     * Checks if the ordered quantity can be fulfilled.
     * If yes: deducts the stock and returns a success message.
     * If no:  returns a failure message without touching stock.
     */
    public String processOrder(String productId, int requestedQuantity) {
        Product product = productStore.get(productId);

        if (product == null) {
            return "Product not found in inventory: " + productId;
        }

        if (product.getQuantity() >= requestedQuantity) {
            int newQuantity = product.getQuantity() - requestedQuantity;
            product.setQuantity(newQuantity);
            return String.format(
                "Order FULFILLED for product '%s' [%s]. Requested: %d | Remaining stock: %d",
                product.getName(), productId, requestedQuantity, newQuantity
            );
        } else {
            return String.format(
                "Order REJECTED for product '%s' [%s]. Requested: %d but only %d in stock.",
                product.getName(), productId, requestedQuantity, product.getQuantity()
            );
        }
    }

    public Map<String, Product> getAllProducts() {
        return productStore;
    }
}
